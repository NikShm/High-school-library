package com.HighSchoolLibrary.services.impls;


import com.HighSchoolLibrary.dto.BookMap;
import com.HighSchoolLibrary.dto.OrderDTO;
import com.HighSchoolLibrary.dto.PageDTO;
import com.HighSchoolLibrary.dto.search.SearchDTO;
import com.HighSchoolLibrary.dto.search.SearchPattern;
import com.HighSchoolLibrary.entities.Book;
import com.HighSchoolLibrary.entities.Order;
import com.HighSchoolLibrary.entities.Penalty;
import com.HighSchoolLibrary.entities.users.User;
import com.HighSchoolLibrary.enums.SortDirection;
import com.HighSchoolLibrary.exceptions.DatabaseFetchException;
import com.HighSchoolLibrary.mappers.AuthorMapper;
import com.HighSchoolLibrary.mappers.OrderMapper;
import com.HighSchoolLibrary.repositoriesJPA.AuthorRepository;
import com.HighSchoolLibrary.repositoriesJPA.BookRepository;
import com.HighSchoolLibrary.repositoriesJPA.UsersRepository;
import com.HighSchoolLibrary.repositoriesMongo.OrderRepository;
import com.HighSchoolLibrary.repositoriesMongo.PenaltyRepository;
import com.HighSchoolLibrary.services.OrderService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

/*
@author Микола
@project High_school_library
@class OrderServiceImpl
@version 1.0.0
@since 05.09.2022 - 22.29
*/
@Service
public class OrderServiceImpl implements OrderService {
    private final MongoTemplate mongoTemplate;
    private final OrderMapper mapper;
    private final OrderRepository orderRepository;
    private final UsersRepository usersRepository;
    private final BookRepository bookRepository;
    private final PenaltyRepository penaltyRepository;
    private final ZoneId zid = ZoneId.of("Europe/Kiev");


    public OrderServiceImpl(MongoTemplate mongoTemplate, AuthorRepository authorRepository, AuthorMapper authorMapper, OrderMapper mapper, OrderRepository orderRepository, UsersRepository usersRepository, BookRepository bookRepository, PenaltyRepository penaltyRepository) {
        this.mongoTemplate = mongoTemplate;
        this.mapper = mapper;
        this.orderRepository = orderRepository;
        this.usersRepository = usersRepository;
        this.bookRepository = bookRepository;
        this.penaltyRepository = penaltyRepository;
    }

    @Override
    public PageDTO<OrderDTO> getAll(SearchDTO<SearchPattern> search) {
        Sort sort = Sort.by(search.getSortField());
        if (search.getSortDirection() == SortDirection.DESC) {
            sort = sort.descending();
        }
        Query queryPage = new Query();
        PageDTO<OrderDTO> dto = new PageDTO<>();
        queryPage.addCriteria(Criteria.where("id_user").is(Integer.parseInt(search.getSearchPattern().getSearch())));
        dto.setTotalItem(mongoTemplate.count(queryPage, Order.class));
        Pageable pageable = PageRequest.of(search.getPage(), search.getPageSize(), sort);
        queryPage.with(pageable);
        List<Order> orders = mongoTemplate.find(queryPage, Order.class);
        dto.setContent(orders.stream().map(mapper::toDto).collect(Collectors.toList()));
        System.out.println(dto.getContent());
        return dto;
    }

    @Override
    public List<BookMap> getCount(List<Integer> ids) {
        Aggregation agg = newAggregation(
                match(Criteria.where("id_book").in(ids).and("status").is("Здано")),
                group("id_book").count().as("count"),
                project("count").and("id_book").previousOperation(),
                sort(Sort.Direction.DESC, "id_book")

        );
        AggregationResults<BookMap> groupResults
                = mongoTemplate.aggregate(agg, Order.class, BookMap.class);
        List<BookMap> result = groupResults.getMappedResults();
        System.out.println(result);
        return result;
    }

    @Override
    public void create(OrderDTO orderDTO) {
        usersRepository.
                findById(orderDTO.getIdUser())
                .orElseThrow(() -> new DatabaseFetchException(orderDTO.getIdUser(), User.class.getSimpleName()));
        bookRepository.
                findById(orderDTO.getBook().getId())
                .orElseThrow(() -> new DatabaseFetchException(orderDTO.getBook().getId(), Book.class.getSimpleName()));
        ZoneId zid = ZoneId.of("Europe/Kiev");
        orderDTO.setOrderDate(LocalDateTime.now(zid));
        orderDTO.setStatus("Замовленно");
        orderRepository.save(mapper.toEntity(orderDTO));
    }

    @Override
    public void toIssue(OrderDTO orderDTO) {
        User user = usersRepository.
                findById(orderDTO.getIdUser())
                .orElseThrow(() -> new DatabaseFetchException(orderDTO.getIdUser(), User.class.getSimpleName()));
        bookRepository.
                findById(orderDTO.getBook().getId())
                .orElseThrow(() -> new DatabaseFetchException(orderDTO.getBook().getId(), Book.class.getSimpleName()));
//        Order order = orderRepository.findById(orderDTO.getId())
//                .orElseThrow(() -> new DatabaseFetchException(Integer.parseInt(orderDTO.getId()), Order.class.getSimpleName()));
        orderDTO.setDateOfIssue(LocalDateTime.now(zid));
        if (orderDTO.getDateOfIssue() != null) {
            switch (user.getType()) {
                case "Student" -> orderDTO.setReturnDate(orderDTO.getDateOfIssue().plusSeconds(20));
                case "Teacher" -> orderDTO.setReturnDate(orderDTO.getDateOfIssue().plusSeconds(30));
                case "Administrator" -> orderDTO.setReturnDate(orderDTO.getDateOfIssue().plusSeconds(40));
                case "Librarian" -> orderDTO.setReturnDate(orderDTO.getDateOfIssue().plusSeconds(35));
            }
        }
        orderDTO.setStatus("Взято");
        orderRepository.save(mapper.toEntity(orderDTO));
    }

    @Override
    public void abolition(OrderDTO orderDTO){
        orderDTO.setStatus("Відмінено");
        orderRepository.save(mapper.toEntity(orderDTO));
    }

    @Scheduled(fixedDelay = 10000)
    private void checkOrder() {
        Query queryPage = new Query();
        orderRepository.saveAll(mongoTemplate.find(queryPage, Order.class).stream().peek(order -> {
            if (order.getReturnDate() != null) {
                if (order.getReturnDate().isBefore(LocalDateTime.now(zid))) {
                    List<Penalty> penalties = penaltyRepository.findAllByIdAccuserAndIdBookAndStatus(order.getIdUser(),order.getIdBook(), "Неоплачено");
                    if (penalties.isEmpty()) {
                        order.setStatus("Запізненння");
                        Book book = bookRepository.
                                findById(order.getIdBook()).orElseThrow(() -> new DatabaseFetchException(order.getIdBook(), Book.class.getSimpleName()));
                        Penalty penalty = new Penalty();
                        penalty.setPrice(book.getPrice());
                        penalty.setStatus("Неоплачено");
                        penalty.setCurrency("UAH");
                        penalty.setIdBook(order.getIdBook());
                        penalty.setDescription("Не повернув вчасно книжку " + book.getName());
                        penalty.setIdAccuser(order.getIdUser());
                        penaltyRepository.save(penalty);
                    }
                }
            }
        }).toList());
    }
}

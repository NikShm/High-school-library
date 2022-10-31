package com.HighSchoolLibrary.services.impls;


import com.HighSchoolLibrary.dto.BookMap;
import com.HighSchoolLibrary.dto.PageDTO;
import com.HighSchoolLibrary.dto.PenaltyDTO;
import com.HighSchoolLibrary.dto.search.PenaltySearch;
import com.HighSchoolLibrary.dto.search.SearchDTO;
import com.HighSchoolLibrary.entities.Book;
import com.HighSchoolLibrary.entities.Order;
import com.HighSchoolLibrary.entities.Penalty;
import com.HighSchoolLibrary.entities.users.User;
import com.HighSchoolLibrary.enums.RoleType;
import com.HighSchoolLibrary.enums.SortDirection;
import com.HighSchoolLibrary.exceptions.DatabaseFetchException;
import com.HighSchoolLibrary.mappers.PenaltyMapper;
import com.HighSchoolLibrary.repositoriesJPA.BookRepository;
import com.HighSchoolLibrary.repositoriesJPA.UsersRepository;
import com.HighSchoolLibrary.repositoriesMongo.PenaltyRepository;
import com.HighSchoolLibrary.services.PenaltyService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/*
@author Микола
@project High_school_library
@class PenaltyServiceImpl
@version 1.0.0
@since 18.08.2022 - 16.35
*/
@Service
public class PenaltyServiceImpl implements PenaltyService {

    private final PenaltyMapper mapper;
    private final MongoTemplate mongoTemplate;
    private final PenaltyRepository penaltyRepository;
    private final BookRepository bookRepository;
    private final UsersRepository usersRepository;
    private final OrderServiceImpl orderService;
    private final EntityManager entityManager;

    public PenaltyServiceImpl(PenaltyMapper mapper, MongoTemplate mongoTemplate, PenaltyRepository penaltyRepository, BookRepository bookRepository, UsersRepository usersRepository, OrderServiceImpl orderService, EntityManager entityManager) {
        this.mapper = mapper;
        this.mongoTemplate = mongoTemplate;
        this.penaltyRepository = penaltyRepository;
        this.bookRepository = bookRepository;
        this.usersRepository = usersRepository;
        this.orderService = orderService;
        this.entityManager = entityManager;
    }

    @Override
    public PageDTO<PenaltyDTO> getAll(SearchDTO<PenaltySearch> search) {
        Sort sort = Sort.by(search.getSortField());
        if (search.getSortDirection() == SortDirection.DESC) {
            sort = sort.descending();
        }
        Query queryPage = new Query();
        PageDTO<PenaltyDTO> dto = new PageDTO<>();
        if (search.getSearchPattern() != null) {
            queryPage.addCriteria(Criteria.where("id_penalty_kicker").is(Integer.parseInt(search.getSearchPattern().getSearch())));
        }
        dto.setTotalItem(mongoTemplate.count(queryPage,Penalty.class));
        Pageable pageable = PageRequest.of(search.getPage(), search.getPageSize(), sort);
        queryPage.with(pageable);
        List<Penalty> penalties = mongoTemplate.find(queryPage, Penalty.class);
        dto.setContent(penalties.stream().map(mapper::toDto).collect(Collectors.toList()));
        return dto;
    }

    @Override
    public void pay(String idPenalty){
        Penalty penalty =  penaltyRepository.findById(idPenalty)
                .orElseThrow(() -> new DatabaseFetchException(Integer.parseInt(idPenalty), Order.class.getSimpleName()));
        penalty.setStatus("Оплачено");
        penaltyRepository.save(penalty);
    }

    @Override
    public void cancel(Integer id, String penaltyId) {
        Penalty penalty = penaltyRepository.findById(penaltyId)
                .orElseThrow(() -> new DatabaseFetchException(Integer.parseInt(penaltyId), Order.class.getSimpleName()));
        if (Objects.equals(id, penalty.getIdPenaltyKicker()) && Objects.equals(penalty.getStatus(), "Замовленно")) {
            penalty.setStatus("Відмінено");
            penaltyRepository.save(penalty);
        }
    }

    @Override
    @Transactional
    public Boolean create(PenaltyDTO penaltyDTO){
        User user = usersRepository.
                findById(penaltyDTO.getIdPenaltyKicker())
                .orElseThrow(() -> new DatabaseFetchException(penaltyDTO.getIdPenaltyKicker(), User.class.getSimpleName()));
        Book book = bookRepository.
                findById(penaltyDTO.getIdBook())
                .orElseThrow(() -> new DatabaseFetchException(penaltyDTO.getIdBook(), Book.class.getSimpleName()));
        List<Integer> ids = new ArrayList<>();
        ids.add(book.getId());
        List<BookMap> countsOrder = orderService.getCount(ids);
        if (!countsOrder.isEmpty()){
            book.setCount(book.getCount() - countsOrder.get(0).getCount());
        }
        if (book.getCount() > 0) {
            Penalty penalty = new Penalty();
            switch (user.getType()) {
                case "Student" -> penalty.setPrice(book.getPrice() * 2);
                case "Teacher" -> penalty.setPrice(book.getPrice() * 3);
                case "Administrator" -> penalty.setPrice(book.getPrice() * 4);
                case "Librarian" -> penalty.setPrice(book.getPrice() * 5);
            }
            penalty.setIdBook(penaltyDTO.getIdBook());
            penalty.setIdPenaltyKicker(penaltyDTO.getIdPenaltyKicker());
            penalty.setIdAccuser(penaltyDTO.getIdAccuser());
            penalty.setCurrency("UAH");
            penalty.setDescription(penaltyDTO.getDescription());
            penalty.setStatus("Неоплачено");
            penaltyRepository.save(penalty);
            book.setCount(book.getCount() - 1 + countsOrder.get(0).getCount());

            bookRepository.save(book);
//            entityManager.createNativeQuery("update book set count = ?1 where id = ?2").setParameter(1, book.getCount())
//                    .setParameter(2, book.getId())
//                    .executeUpdate();
            return true;
        }
        return false;
    }
}

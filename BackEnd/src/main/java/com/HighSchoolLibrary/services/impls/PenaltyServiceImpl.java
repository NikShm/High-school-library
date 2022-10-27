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
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

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

    public PenaltyServiceImpl(PenaltyMapper mapper, MongoTemplate mongoTemplate, PenaltyRepository penaltyRepository, BookRepository bookRepository, UsersRepository usersRepository) {
        this.mapper = mapper;
        this.mongoTemplate = mongoTemplate;
        this.penaltyRepository = penaltyRepository;
        this.bookRepository = bookRepository;
        this.usersRepository = usersRepository;
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
    public List<BookMap> getCount(List<Integer> ids) {
        Aggregation agg = newAggregation(
                match(Criteria.where("id_book").in(ids).and("description").regex("Спортив книжку")),
                group("id_book").count().as("count"),
                project("count").and("id_book").previousOperation()

        );
        AggregationResults<BookMap> groupResults
                = mongoTemplate.aggregate(agg, Penalty.class, BookMap.class);
        List<BookMap> result = groupResults.getMappedResults();
        System.out.println(result);
        return result;
    }

    @Override
    public void pay(String idPenalty){
        Penalty penalty =  penaltyRepository.findById(idPenalty)
                .orElseThrow(() -> new DatabaseFetchException(Integer.parseInt(idPenalty), Order.class.getSimpleName()));
        penalty.setStatus("Оплачено");
        penaltyRepository.save(penalty);
    }

    @Override
    public void create(PenaltyDTO penaltyDTO){
        User user = usersRepository.
                findById(penaltyDTO.getIdPenaltyKicker())
                .orElseThrow(() -> new DatabaseFetchException(penaltyDTO.getIdPenaltyKicker(), User.class.getSimpleName()));
        Book book = bookRepository.
                findById(penaltyDTO.getIdBook())
                .orElseThrow(() -> new DatabaseFetchException(penaltyDTO.getIdBook(), Book.class.getSimpleName()));
        if (book.getCount() > penaltyRepository.findAllByIdBook(penaltyDTO.getIdBook()).size()) {
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
        }
    }
}

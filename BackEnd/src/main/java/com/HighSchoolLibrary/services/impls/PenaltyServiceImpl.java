package com.HighSchoolLibrary.services.impls;


import com.HighSchoolLibrary.dto.BookMap;
import com.HighSchoolLibrary.dto.PageDTO;
import com.HighSchoolLibrary.dto.PenaltyDTO;
import com.HighSchoolLibrary.dto.search.SearchDTO;
import com.HighSchoolLibrary.dto.search.SearchPattern;
import com.HighSchoolLibrary.entities.Order;
import com.HighSchoolLibrary.entities.Penalty;
import com.HighSchoolLibrary.enums.SortDirection;
import com.HighSchoolLibrary.mappers.PenaltyMapper;
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

    public PenaltyServiceImpl(PenaltyMapper mapper, MongoTemplate mongoTemplate) {
        this.mapper = mapper;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public PageDTO<PenaltyDTO> getAll(SearchDTO<SearchPattern> search) {
        Sort sort = Sort.by(search.getSortField());
        if (search.getSortDirection() == SortDirection.DESC) {
            sort = sort.descending();
        }
        Query queryPage = new Query();
        PageDTO<PenaltyDTO> dto = new PageDTO<>();
        queryPage.addCriteria(Criteria.where("id_penalty_kicker").is(Integer.parseInt(search.getSearchPattern().getSearch())));
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
                match(Criteria.where("id_book").in(ids).and("description").is("Загубив книжку")),
                group("id_book").count().as("count"),
                project("count").and("id_book").previousOperation()

        );
        AggregationResults<BookMap> groupResults
                = mongoTemplate.aggregate(agg, Order.class, BookMap.class);
        List<BookMap> result = groupResults.getMappedResults();
        System.out.println(result);
        return result;
    }
}

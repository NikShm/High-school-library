package com.HighSchoolLibrary.services.impls;


import com.HighSchoolLibrary.dto.OrderDTO;
import com.HighSchoolLibrary.dto.PageDTO;
import com.HighSchoolLibrary.dto.search.SearchDTO;
import com.HighSchoolLibrary.entities.Order;
import com.HighSchoolLibrary.enums.SortDirection;
import com.HighSchoolLibrary.mappers.AuthorMapper;
import com.HighSchoolLibrary.mappers.OrderMapper;
import com.HighSchoolLibrary.repositoriesJPA.AuthorRepository;
import com.HighSchoolLibrary.services.OrderService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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


    public OrderServiceImpl(MongoTemplate mongoTemplate, AuthorRepository authorRepository, AuthorMapper authorMapper, OrderMapper mapper) {
        this.mongoTemplate = mongoTemplate;
        this.mapper = mapper;
    }
    @Override
    public PageDTO<OrderDTO> getAll(SearchDTO search) {
        Sort sort = Sort.by(search.getSortField());
        if (search.getSortDirection() == SortDirection.DESC) {
            sort = sort.descending();
        }
        Query queryPage = new Query();
        PageDTO<OrderDTO> dto = new PageDTO<>();
        queryPage.addCriteria(Criteria.where("id_user").is(Integer.parseInt(search.getSearch())));
        dto.setTotalItem(mongoTemplate.count(queryPage, Order.class));
        Pageable pageable = PageRequest.of(search.getPage(), search.getPageSize(), sort);
        queryPage.with(pageable);
        List<Order> orders = mongoTemplate.find(queryPage, Order.class);
        dto.setContent(orders.stream().map(mapper::toDto).collect(Collectors.toList()));
        System.out.println(dto.getContent());
        return dto;
    }
}

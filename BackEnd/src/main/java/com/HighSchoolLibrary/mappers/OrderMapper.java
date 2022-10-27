package com.HighSchoolLibrary.mappers;


import com.HighSchoolLibrary.dto.BookDTO;
import com.HighSchoolLibrary.dto.OrderDTO;
import com.HighSchoolLibrary.entities.Order;
import com.HighSchoolLibrary.repositoriesJPA.BookRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

/*
@author Микола
@project High_school_library
@class OrderMapper
@version 1.0.0
@since 05.09.2022 - 22.52
*/
@Component
public class OrderMapper {
     private final BookMapper mapper;
     private final BookRepository repository;

    public OrderMapper(BookMapper mapper, BookRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public OrderDTO toDto(Order order) {
        OrderDTO dto = new OrderDTO(order);
        dto.setBook(repository.findAllById(order.getIdBook()).stream().map(mapper::toDto).toList().get(0));
        return dto;
    }

    public Order toEntity(OrderDTO orderDTO) {
        Order entity = new Order();
        entity.setIdUser(orderDTO.getIdUser());
        entity.setDateOfIssue(orderDTO.getDateOfIssue());
        entity.setIdBook(orderDTO.getBook().getId());
        entity.setOrderDate(orderDTO.getOrderDate());
        entity.setReturnDate(orderDTO.getReturnDate());
        entity.setStatus(orderDTO.getStatus());
        return entity;
    }
}

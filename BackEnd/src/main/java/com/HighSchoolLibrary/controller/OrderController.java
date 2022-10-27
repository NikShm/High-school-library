package com.HighSchoolLibrary.controller;

import com.HighSchoolLibrary.dto.OrderDTO;
import com.HighSchoolLibrary.dto.PageDTO;
import com.HighSchoolLibrary.dto.search.OrderSearch;
import com.HighSchoolLibrary.dto.search.SearchDTO;
import com.HighSchoolLibrary.dto.search.SearchPattern;
import com.HighSchoolLibrary.entities.users.User;
import com.HighSchoolLibrary.enums.RoleType;
import com.HighSchoolLibrary.exceptions.DatabaseFetchException;
import com.HighSchoolLibrary.repositoriesJPA.UsersRepository;
import com.HighSchoolLibrary.repositoriesMongo.OrderRepository;
import com.HighSchoolLibrary.services.impls.OrderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


/*
@author Микола
@project High_school_library
@class OrderController
@version 1.0.0
@since 05.09.2022 - 23.16
*/
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/order", produces = "application/json")
public class OrderController {
    private final OrderServiceImpl orderService;
    private final UsersRepository usersRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    public OrderController(OrderServiceImpl service, UsersRepository usersRepository, OrderRepository orderRepository, UsersRepository usersRepository1) {
        this.orderService = service;
        this.usersRepository = usersRepository1;
    }

    @PostMapping("/search")
    public PageDTO<OrderDTO> getAll(@RequestBody SearchDTO<OrderSearch> search) {
        LOGGER.info("search(search={})", search);
        return orderService.getAll(search);
    }

    @PostMapping("/create")
    public void create(@RequestBody OrderDTO orderDTO) {
        orderService.create(orderDTO);
    }

    @RequestMapping(value = "/to-issue/{id}")
    public void toIssue(@PathVariable("id") Integer id, @RequestBody OrderDTO orderDTO) {
        User user = usersRepository.
                findById(id)
                .orElseThrow(() -> new DatabaseFetchException(id, User.class.getSimpleName()));
        if (!user.getRole().equals(RoleType.USER)){
            orderService.toIssue(orderDTO);
        }
    }

    @PostMapping(value = "/abolition/{id}")
    public void abolition(@PathVariable("id") Integer id, @RequestBody OrderDTO orderDTO) {
            orderService.abolition(id, orderDTO);
    }

}

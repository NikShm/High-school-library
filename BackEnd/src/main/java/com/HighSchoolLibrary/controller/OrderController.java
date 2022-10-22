package com.HighSchoolLibrary.controller;

import com.HighSchoolLibrary.dto.OrderDTO;
import com.HighSchoolLibrary.dto.PageDTO;
import com.HighSchoolLibrary.dto.PenaltyDTO;
import com.HighSchoolLibrary.dto.SearchDTO;
import com.HighSchoolLibrary.services.impls.OrderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


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
    private final OrderServiceImpl service;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    public OrderController(OrderServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/search")
    public PageDTO<OrderDTO> getAll(@RequestBody SearchDTO search) {
        LOGGER.info("search(search={})", search);
        return service.getAll(search);
    }
}

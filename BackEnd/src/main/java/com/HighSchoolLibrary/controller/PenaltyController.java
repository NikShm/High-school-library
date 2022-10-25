package com.HighSchoolLibrary.controller;


import com.HighSchoolLibrary.dto.PageDTO;
import com.HighSchoolLibrary.dto.PenaltyDTO;
import com.HighSchoolLibrary.dto.search.SearchDTO;
import com.HighSchoolLibrary.services.impls.PenaltyServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/*
@author Микола
@project High_school_library
@class PenaltyController
@version 1.0.0
@since 01.09.2022 - 15.00
*/
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/penalty", produces = "application/json")
public class PenaltyController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final PenaltyServiceImpl penaltyService;

    public PenaltyController(PenaltyServiceImpl penaltyService) {
        this.penaltyService = penaltyService;
    }

    @PostMapping("/search")
    public PageDTO<PenaltyDTO> getAll(@RequestBody SearchDTO search) {
        LOGGER.info("search(search={})", search);
        return penaltyService.getAll(search);
    }
}

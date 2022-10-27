package com.HighSchoolLibrary.controller;


import com.HighSchoolLibrary.dto.OrderDTO;
import com.HighSchoolLibrary.dto.PageDTO;
import com.HighSchoolLibrary.dto.PenaltyDTO;
import com.HighSchoolLibrary.dto.search.PenaltySearch;
import com.HighSchoolLibrary.dto.search.SearchDTO;
import com.HighSchoolLibrary.entities.users.User;
import com.HighSchoolLibrary.enums.RoleType;
import com.HighSchoolLibrary.exceptions.DatabaseFetchException;
import com.HighSchoolLibrary.repositoriesJPA.UsersRepository;
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
    private final UsersRepository usersRepository;

    public PenaltyController(PenaltyServiceImpl penaltyService, UsersRepository usersRepository) {
        this.penaltyService = penaltyService;
        this.usersRepository = usersRepository;
    }

    @PostMapping("/search")
    public PageDTO<PenaltyDTO> getAll(@RequestBody SearchDTO<PenaltySearch> search) {
        LOGGER.info("search(search={})", search);
        return penaltyService.getAll(search);
    }

    @GetMapping("/{id}/{idPenalty}")
    public void  pay(@PathVariable("id") Integer id, @PathVariable("idPenalty") String idPenalty){
        User user = usersRepository.
                findById(id)
                .orElseThrow(() -> new DatabaseFetchException(id, User.class.getSimpleName()));
        if (!user.getRole().equals(RoleType.USER)){
            penaltyService.pay(idPenalty);
        }
    }

    @RequestMapping(value = "/create")
    public void toIssue(@RequestBody PenaltyDTO penaltyDTO) {
        User user = usersRepository.
                findById(penaltyDTO.getIdAccuser())
                .orElseThrow(() -> new DatabaseFetchException(penaltyDTO.getIdAccuser(), User.class.getSimpleName()));
        if (!user.getRole().equals(RoleType.USER)){
            penaltyService.create(penaltyDTO);
        }
    }
}

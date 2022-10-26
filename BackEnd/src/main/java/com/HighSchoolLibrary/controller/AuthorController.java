package com.HighSchoolLibrary.controller;


import com.HighSchoolLibrary.dto.*;
import com.HighSchoolLibrary.dto.search.SearchDTO;
import com.HighSchoolLibrary.dto.search.SearchPattern;
import com.HighSchoolLibrary.services.impls.AuthorServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/*
@author Микола
@project High-school-library
@class AuthorController
@version 1.0.0
@since 25.10.2022 - 11.51
*/
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/authors", produces = "application/json")
public class AuthorController {

    private final AuthorServiceImpl service;

    public AuthorController(AuthorServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/search")
    public PageDTO<AuthorDTO> search(@RequestBody SearchDTO<SearchPattern> search) {
        return service.getPage(search);
    }

    @GetMapping("/id={id}")
    public Optional<AuthorDTO> getOneAuthoe(@PathVariable Integer id) {
        return service.getOneAuthor(id);
    }
}

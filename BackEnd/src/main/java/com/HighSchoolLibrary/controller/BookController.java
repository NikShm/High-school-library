package com.HighSchoolLibrary.controller;


/*
@author Микола
@project High-school-library
@class BookController
@version 1.0.0
@since 24.10.2022 - 21.33
*/

import com.HighSchoolLibrary.dto.BookDTO;
import com.HighSchoolLibrary.dto.PageDTO;
import com.HighSchoolLibrary.dto.search.SearchDTO;
import com.HighSchoolLibrary.dto.search.SearchPattern;
import com.HighSchoolLibrary.services.impls.BookServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/books", produces = "application/json")
public class BookController {

    private final BookServiceImpl service;

    public BookController(BookServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/search")
    public PageDTO<BookDTO> search(@RequestBody SearchDTO<SearchPattern> search) {
        return service.getPage(search);
    }
}

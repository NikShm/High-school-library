package com.HighSchoolLibrary.services.impls;


import com.HighSchoolLibrary.dto.PageDTO;
import com.HighSchoolLibrary.dto.SearchDTO;
import com.HighSchoolLibrary.dto.UserDTO;
import com.HighSchoolLibrary.entities.Book;
import com.HighSchoolLibrary.entities.User;
import com.HighSchoolLibrary.enums.SortDirection;
import com.HighSchoolLibrary.mappers.BookMapper;
import com.HighSchoolLibrary.mappers.UserMapper;
import com.HighSchoolLibrary.repositoriesJPA.BookRepository;
import com.HighSchoolLibrary.services.BookService;
import com.HighSchoolLibrary.utils.QueryHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
@author Микола
@project High_school_library
@class BookServiceImpl
@version 1.0.0
@since 05.09.2022 - 22.05
*/
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository repository;
    private final BookMapper mapper;

    public BookServiceImpl(BookRepository repository, BookMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public PageDTO<UserDTO> getPage(SearchDTO search) {
        return null;
    }

    @Override
    public Optional<UserDTO> getOneUser(Integer id) {
        return Optional.empty();
    }
}

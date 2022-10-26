package com.HighSchoolLibrary.services.impls;


import com.HighSchoolLibrary.dto.AuthorDTO;
import com.HighSchoolLibrary.dto.PageDTO;
import com.HighSchoolLibrary.dto.search.SearchDTO;
import com.HighSchoolLibrary.dto.search.SearchPattern;
import com.HighSchoolLibrary.entities.Author;
import com.HighSchoolLibrary.mappers.AuthorMapper;
import com.HighSchoolLibrary.repositoriesJPA.AuthorRepository;
import com.HighSchoolLibrary.services.AuthorService;
import com.HighSchoolLibrary.utils.QueryHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
@project High-school-library
@class AuthorServiceImpl
@version 1.0.0
@since 25.10.2022 - 11.52
*/
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;
    private final AuthorMapper mapper;

    public AuthorServiceImpl(AuthorRepository repository, AuthorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<AuthorDTO> getOneAuthor(Integer id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public PageDTO<AuthorDTO> getPage(SearchDTO<SearchPattern> search) {
        Pageable pageable = PageRequest.of(search.getPage(), search.getPageSize(), QueryHelper.getSort(search.getSortDirection()
                , search.getSortField()));
        Page<Author> all = repository.findAll((root, query, criteriaBuilder) -> getPredicate(search, criteriaBuilder, root), pageable);
        PageDTO<AuthorDTO> dto = new PageDTO<>();
        dto.setContent(all.stream().map(mapper::toDto).collect(Collectors.toList()));
        dto.setTotalItem(all.getTotalElements());
        return dto;
    }

    private Predicate getPredicate(SearchDTO<SearchPattern> search, CriteriaBuilder criteriaBuilder, Root<Author> user) {
        List<Predicate> predicates = new ArrayList<>();
        String value = search.getSearchPattern().getSearch();
        if (value != null) {
            predicates.add(QueryHelper.ilike(user.get("name"), criteriaBuilder, value));
        }
        return predicates.size() == 1 ? predicates.get(0) : criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}

package com.HighSchoolLibrary.services.impls;


import com.HighSchoolLibrary.dto.PageDTO;
import com.HighSchoolLibrary.dto.SearchDTO;
import com.HighSchoolLibrary.dto.UserDTO;
import com.HighSchoolLibrary.entities.User;
import com.HighSchoolLibrary.enums.SortDirection;
import com.HighSchoolLibrary.exceptions.DatabaseFetchException;
import com.HighSchoolLibrary.mappers.UserMapper;
import com.HighSchoolLibrary.repositoriesJPA.UsersRepository;
import com.HighSchoolLibrary.services.UserService;
import com.HighSchoolLibrary.utils.QueryHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
@author Микола
@project High_school_library
@class UserServiceImpl
@version 1.0.0
@since 17.08.2022 - 22.31
*/
@Service
public class UserServiceImpl implements UserService {

    private final UsersRepository repository;
    private final UserMapper mapper;

    public UserServiceImpl(UsersRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<UserDTO> getAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public PageDTO<UserDTO> getPage(SearchDTO search) {
        Sort sort = Sort.by(search.getSortField());
        if (search.getSortDirection() == SortDirection.DESC) {
            sort = sort.descending();
        }
        Pageable pageable = PageRequest.of(search.getPage(), search.getPageSize(), sort);
        Page<User> all = repository.findAll((root, query, criteriaBuilder) -> getPredicate(search, criteriaBuilder, root), pageable);
        PageDTO<UserDTO> dto = new PageDTO<>();
        dto.setContent(all.stream().map(mapper::toDto).collect(Collectors.toList()));
        dto.setPageCount(all.getTotalPages());
        dto.setPage(all.getNumber());
        dto.setPageSize(all.getNumberOfElements());
        dto.setTotalItem(all.getTotalElements());
        return dto;
    }

    private Predicate getPredicate(SearchDTO search, CriteriaBuilder criteriaBuilder, Root<User> product) {
        List<Predicate> predicates = new ArrayList<>();
        String value = search.getSearch();
        if (value != null) {
            predicates.add(criteriaBuilder.or(QueryHelper.ilike(product.get("surname"), criteriaBuilder, value),
                    QueryHelper.ilike(product.get("name"), criteriaBuilder, value)));
        }
        return predicates.size() == 1 ? predicates.get(0) : criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    @Override
    public Optional<UserDTO> getOneUser(Integer id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public void update(UserDTO dto) throws IOException {
        repository.findById(dto.getId()).orElseThrow(() -> new DatabaseFetchException(dto.getId(), User.class.getSimpleName()));
        User updatedUser = mapper.toEntity(dto);
        repository.save(updatedUser);
    }

    @Override
    public void delete(Integer id) throws IOException {
        repository.findById(id).orElseThrow(() -> new DatabaseFetchException(id, User.class.getSimpleName()));
        repository.deleteById(id);
    }

    @Override
    public Integer create(UserDTO dto) {
        User updatedUser = mapper.toEntity(dto);
        return repository.save(updatedUser).getId();
    }
}

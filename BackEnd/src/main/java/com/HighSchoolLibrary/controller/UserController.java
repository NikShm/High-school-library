package com.HighSchoolLibrary.controller;


import com.HighSchoolLibrary.dto.*;
import com.HighSchoolLibrary.dto.search.UserSearch;
import com.HighSchoolLibrary.dto.usersDTO.LibrarianDTO;
import com.HighSchoolLibrary.dto.usersDTO.StudentDTO;
import com.HighSchoolLibrary.dto.usersDTO.TeacherDTO;
import com.HighSchoolLibrary.dto.usersDTO.UserDTO;
import com.HighSchoolLibrary.dto.search.SearchDTO;
import com.HighSchoolLibrary.entities.users.User;
import com.HighSchoolLibrary.enums.RoleType;
import com.HighSchoolLibrary.exceptions.DatabaseFetchException;
import com.HighSchoolLibrary.mappers.AuthorMapper;
import com.HighSchoolLibrary.repositoriesJPA.AuthorRepository;
import com.HighSchoolLibrary.repositoriesJPA.UsersRepository;
import com.HighSchoolLibrary.services.impls.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
@author Микола
@project High_school_library
@class UserController
@version 1.0.0
@since 17.08.2022 - 22.29
*/
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/users", produces = "application/json")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserServiceImpl service;
    private  final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final UsersRepository usersRepository;

    public UserController(UserServiceImpl service, AuthorRepository authorRepository, AuthorMapper authorMapper, UsersRepository usersRepository) {
        this.service = service;
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
        this.usersRepository = usersRepository;
    }

    @ApiOperation(value = "View a page of users", response = PageDTO.class,
            notes = "Get search object where describe what page need, page size, sort field, sort type and name or surname user. Return PageDTO object.",
            tags={ "get-page"})
    @PostMapping("/search")
    public PageDTO<UserDTO> search(@RequestBody SearchDTO<UserSearch> search) {
        LOGGER.info("search(search={})", search);
        return service.getPage(search);
    }
    @ApiOperation(value = "Get user", response = User.class,
            notes = "Get user id an return UserDto object.",
            tags={ "get-one-element"})
    @GetMapping("/id={id}")
    public Optional<UserDTO> getOneUser(@PathVariable Integer id) {
        return service.getOneUser(id);
    }

    @ApiOperation(value = "Created student", response = Integer.class,
            notes = "Gets StudentDTO, and set it into database.",
            tags={ "created-user"})
    @PostMapping("/create-student/{id}")
    public Integer create(@PathVariable("id") Integer id, @RequestBody StudentDTO dto){
        User user = usersRepository.
                findById(id)
                .orElseThrow(() -> new DatabaseFetchException(id, User.class.getSimpleName()));
        if (user.getRole().equals(RoleType.ADMIN)) {
            return service.createStudent(dto);
        }
        return null;
    }

    @ApiOperation(value = "Created teacher", response = Integer.class,
            notes = "Gets TeacherDTO, and set it into database.",
            tags={ "created-user"})
    @PostMapping("/create-teacher/{id}")
    public Integer create(@PathVariable("id") Integer id, @RequestBody TeacherDTO dto){
        User user = usersRepository.
                findById(id)
                .orElseThrow(() -> new DatabaseFetchException(id, User.class.getSimpleName()));
        if (user.getRole().equals(RoleType.ADMIN)) {
            return service.createTeacher(dto);
        }
        return null;
    }

    @PostMapping("/create-librarian/{id}")
    public Integer create(@PathVariable("id") Integer id, @RequestBody LibrarianDTO dto){
        User user = usersRepository.
                findById(id)
                .orElseThrow(() -> new DatabaseFetchException(id, User.class.getSimpleName()));
        if (user.getRole().equals(RoleType.ADMIN)) {
            return service.createLibrarian(dto);
        }
        return null;
    }

    @ApiOperation(value = "Update student", httpMethod = "PUT",
            notes = "Gets StudentDTO, and update it by id if it is where in data base.",
            tags={ "update-user"})
    @PutMapping("/update-student/{id}")
    public void update(@PathVariable("id") Integer id,@RequestBody StudentDTO dto) throws IOException {
//        LOGGER.info("userDTO(dto={})", dto);
        service.update(dto);
    }

    @ApiOperation(value = "Update teacher", httpMethod = "PUT",
            notes = "Gets TeacherDTO, and update it by id if it is where in data base.",
            tags={ "update-user"})
    @PutMapping("/update-teacher")
    public void update(@RequestBody TeacherDTO dto) throws IOException {
//        LOGGER.info("userDTO(dto={})", dto);
        service.update(dto);
    }

    @ApiOperation(value = "Deleted teacher", httpMethod = "DELETE",
            notes = "Gets user id, and deleted, it by id if it is where in data base.",
            tags={ "delete-user"})
    @DeleteMapping("/delete/{id}")
    public void deleteOne(@PathVariable Integer id) throws IOException {
        service.delete(id);
    }

    @GetMapping
    public List<AuthorDTO> show() {
        return authorRepository.findAll().stream().map(authorMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/login={login}/password={password}")
    public LogInDTO getRole(@PathVariable String login, @PathVariable String password){
        return service.authorize(login,password);
    }
}

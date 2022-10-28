package com.HighSchoolLibrary.services;/*
@author Микола
@project High_school_library
@class UserService
@version 1.0.0
@since 17.08.2022 - 22.30
*/

import com.HighSchoolLibrary.dto.LogInDTO;
import com.HighSchoolLibrary.dto.PageDTO;
import com.HighSchoolLibrary.dto.search.SearchDTO;
import com.HighSchoolLibrary.dto.search.UserSearch;
import com.HighSchoolLibrary.dto.usersDTO.LibrarianDTO;
import com.HighSchoolLibrary.dto.usersDTO.StudentDTO;
import com.HighSchoolLibrary.dto.usersDTO.TeacherDTO;
import com.HighSchoolLibrary.dto.usersDTO.UserDTO;
import com.HighSchoolLibrary.dto.search.SearchPattern;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> getAll();

    PageDTO<UserDTO> getPage(SearchDTO<UserSearch> search);

    Optional<UserDTO> getOneUser(Integer id);

    void update(UserDTO dto) throws IOException;

    void delete(Integer id) throws IOException;


    @Transactional
    Integer createStudent(StudentDTO dto);

    @Transactional
    Integer createTeacher(TeacherDTO dto);

    @Transactional
    Integer createLibrarian(LibrarianDTO dto);

    LogInDTO authorize(String login, String password);
}

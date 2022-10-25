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
import com.HighSchoolLibrary.dto.UserDTO;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> getAll();

    PageDTO<UserDTO> getPage(SearchDTO search);

    Optional<UserDTO> getOneUser(Integer id);

    void update(UserDTO dto) throws IOException;

    void delete(Integer id) throws IOException;

    Integer create(UserDTO dto);

    LogInDTO authorize(String login, String password);
}

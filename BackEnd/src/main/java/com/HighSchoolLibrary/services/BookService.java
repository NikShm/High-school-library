package com.HighSchoolLibrary.services;/*
@author Микола
@project High_school_library
@class BookService
@version 1.0.0
@since 05.09.2022 - 22.05
*/

import com.HighSchoolLibrary.dto.PageDTO;
import com.HighSchoolLibrary.dto.SearchDTO;
import com.HighSchoolLibrary.dto.UserDTO;

import java.util.Optional;

public interface BookService {
    PageDTO<UserDTO> getPage(SearchDTO search);

    Optional<UserDTO> getOneUser(Integer id);
}

package com.HighSchoolLibrary.services;/*
@author Микола
@project High_school_library
@class AuthorService
@version 1.0.0
@since 05.09.2022 - 22.05
*/

import com.HighSchoolLibrary.dto.AuthorDTO;
import com.HighSchoolLibrary.dto.PageDTO;
import com.HighSchoolLibrary.dto.search.AuthorSearch;
import com.HighSchoolLibrary.dto.search.SearchDTO;
import com.HighSchoolLibrary.dto.search.SearchPattern;

import java.util.Optional;

public interface AuthorService {

    Optional<AuthorDTO> getOneAuthor(Integer id);

    PageDTO<AuthorDTO> getPage(SearchDTO<AuthorSearch> searchDTO);
}

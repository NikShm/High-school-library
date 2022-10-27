package com.HighSchoolLibrary.services;/*
@author Микола
@project High_school_library
@class BookService
@version 1.0.0
@since 05.09.2022 - 22.05
*/

import com.HighSchoolLibrary.dto.BookDTO;
import com.HighSchoolLibrary.dto.PageDTO;
import com.HighSchoolLibrary.dto.search.BookSearch;
import com.HighSchoolLibrary.dto.search.SearchDTO;
import com.HighSchoolLibrary.dto.search.SearchPattern;

public interface BookService {
    PageDTO<BookDTO> getPage(SearchDTO<BookSearch> search);
}

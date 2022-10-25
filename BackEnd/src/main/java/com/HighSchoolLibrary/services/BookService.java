package com.HighSchoolLibrary.services;/*
@author Микола
@project High_school_library
@class BookService
@version 1.0.0
@since 05.09.2022 - 22.05
*/

import com.HighSchoolLibrary.dto.BookDTO;
import com.HighSchoolLibrary.dto.PageDTO;
import com.HighSchoolLibrary.dto.search.SearchAuthorsBookDTO;
import com.HighSchoolLibrary.dto.search.SearchDTO;

public interface BookService {
    PageDTO<BookDTO> getPage(SearchAuthorsBookDTO search);
}

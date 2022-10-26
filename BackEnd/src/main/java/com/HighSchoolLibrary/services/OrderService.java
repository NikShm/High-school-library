package com.HighSchoolLibrary.services;/*
@author Микола
@project High_school_library
@class OrderService
@version 1.0.0
@since 05.09.2022 - 22.29
*/

import com.HighSchoolLibrary.dto.OrderDTO;
import com.HighSchoolLibrary.dto.PageDTO;
import com.HighSchoolLibrary.dto.search.SearchDTO;
import com.HighSchoolLibrary.dto.search.SearchPattern;

public interface OrderService {
    PageDTO<OrderDTO> getAll(SearchDTO<SearchPattern> search);
}

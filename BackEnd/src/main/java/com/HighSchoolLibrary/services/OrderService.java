package com.HighSchoolLibrary.services;/*
@author Микола
@project High_school_library
@class OrderService
@version 1.0.0
@since 05.09.2022 - 22.29
*/

import com.HighSchoolLibrary.dto.BookMap;
import com.HighSchoolLibrary.dto.OrderDTO;
import com.HighSchoolLibrary.dto.PageDTO;
import com.HighSchoolLibrary.dto.search.OrderSearch;
import com.HighSchoolLibrary.dto.search.SearchDTO;
import com.HighSchoolLibrary.dto.search.SearchPattern;

import java.util.List;

public interface OrderService {
    PageDTO<OrderDTO> getAll(SearchDTO<OrderSearch> search);
    List<BookMap> getCount(List<Integer> ids);

    void create(OrderDTO orderDTO);

    void toIssue(OrderDTO orderDTO);

    void abolition(Integer id,OrderDTO orderDTO);
}

package com.HighSchoolLibrary.services;/*
@author Микола
@project High_school_library
@class PenaltyService
@version 1.0.0
@since 18.08.2022 - 16.35
*/

import com.HighSchoolLibrary.dto.PageDTO;
import com.HighSchoolLibrary.dto.PenaltyDTO;
import com.HighSchoolLibrary.dto.SearchDTO;

import java.util.List;

public interface PenaltyService {
    PageDTO<PenaltyDTO> getAll(SearchDTO search);
}


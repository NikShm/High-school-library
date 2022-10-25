package com.HighSchoolLibrary.mappers;


import com.HighSchoolLibrary.dto.AuthorDTO;
import com.HighSchoolLibrary.dto.BookDTO;
import com.HighSchoolLibrary.dto.PenaltyDTO;
import com.HighSchoolLibrary.entities.Author;
import com.HighSchoolLibrary.entities.Book;
import com.HighSchoolLibrary.entities.Penalty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/*
@author Микола
@project High_school_library
@class AuthorMapper
@version 1.0.0
@since 05.09.2022 - 19.45
*/
@Component
public class AuthorMapper {
    public AuthorDTO toDto(Author author) {
        return new AuthorDTO(author);
    }
}

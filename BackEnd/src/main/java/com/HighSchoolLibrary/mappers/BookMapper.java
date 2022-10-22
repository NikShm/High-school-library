package com.HighSchoolLibrary.mappers;


import com.HighSchoolLibrary.dto.AuthorDTO;
import com.HighSchoolLibrary.dto.BookDTO;
import com.HighSchoolLibrary.entities.Author;
import com.HighSchoolLibrary.entities.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
@author Микола
@project High_school_library
@class BookMapper
@version 1.0.0
@since 05.09.2022 - 19.44
*/
@Component
public class BookMapper {
    public BookDTO toDto(Book book) {
        BookDTO dto = new BookDTO(book);
        List<AuthorDTO>authorList = new ArrayList<>();
        for (Author author : book.getAuthorList()){
            AuthorDTO authorDTO = new AuthorDTO(author);
            authorList.add(authorDTO);
        }
        System.out.println(book.getAuthorList());
        dto.setAuthorList(authorList);
        return dto;
    }

}

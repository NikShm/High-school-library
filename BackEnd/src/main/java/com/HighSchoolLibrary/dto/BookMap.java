package com.HighSchoolLibrary.dto;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/*
@author Микола
@project High-school-library
@class BookMap
@version 1.0.0
@since 26.10.2022 - 15.45
*/
public class BookMap {
    private Integer id_book;
    private Integer count;

    public Integer getIdBook() {
        return id_book;
    }

    public void setIdBook(Integer idBook) {
        this.id_book = idBook;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "BookMap{" +
                "idBook='" + id_book + '\'' +
                ", count='" + count + '\'' +
                '}';
    }
}

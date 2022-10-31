package com.HighSchoolLibrary.dto;


import com.HighSchoolLibrary.entities.Book;
import com.HighSchoolLibrary.enums.CategoryType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/*
@author Микола
@project High_school_library
@class BookDTO
@version 1.0.0
@since 05.09.2022 - 19.46
*/
public class BookDTO {
    private Integer id;
    private String name;
    private String description;
    private Integer price;
    private CategoryType category;
    private Integer count;
    private LocalDateTime createdAt;
    private List<AuthorDTO> authorList;

    public BookDTO() {
    }

    public BookDTO(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.description = book.getDescription();
        this.price = book.getPrice();
        this.category = book.getCategory();
        this.count = book.getCount();
        this.createdAt = book.getCreatedAt();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public CategoryType getCategory() {
        return category;
    }

    public void setCategory(CategoryType category) {
        this.category = category;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<AuthorDTO> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<AuthorDTO> authorList) {
        this.authorList = authorList;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", count=" + count +
                ", createdAt='" + createdAt + '\'' +
                ", authorList=" + authorList +
                '}';
    }
}

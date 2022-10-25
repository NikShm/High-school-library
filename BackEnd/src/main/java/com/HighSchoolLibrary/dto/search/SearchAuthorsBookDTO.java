package com.HighSchoolLibrary.dto.search;

import com.HighSchoolLibrary.enums.SortDirection;

/*
@author Микола
@project FreshBeauty
@class SearchDTO
@version 1.0.0
@since 13.07.2022 - 19.34
*/
public class SearchAuthorsBookDTO extends SearchDTO{

    private Integer authorId;

    public SearchAuthorsBookDTO() {
    }

    public SearchAuthorsBookDTO(String search, String sortField, SortDirection sortDirection, Integer page, Integer pageSize, Integer authorId) {
        super(search, sortField,sortDirection, page, pageSize);
        this.authorId = authorId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "SearchAuthorsBookDTO{" +
                "authorId=" + authorId +
                '}';
    }
}

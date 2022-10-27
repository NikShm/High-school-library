package com.HighSchoolLibrary.dto.search;


/*
@author Микола
@project High-school-library
@class SearchPattern
@version 1.0.0
@since 26.10.2022 - 13.11
*/
public class BookSearch {

    private String search;
    private Integer authorId;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }
}

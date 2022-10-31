package com.HighSchoolLibrary.dto.search;


import com.HighSchoolLibrary.enums.RoleType;

/*
@author Микола
@project High-school-library
@class SearchPattern
@version 1.0.0
@since 26.10.2022 - 13.11
*/
public class UserSearch {

    private String search;
    private RoleType role;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserSearch{" +
                "search='" + search + '\'' +
                ", role=" + role +
                '}';
    }
}

package com.HighSchoolLibrary.dto;


import com.HighSchoolLibrary.enums.RoleType;

/*
@author Микола
@project High-school-library
@class LogInDTO
@version 1.0.0
@since 24.10.2022 - 15.26
*/
public class LogInDTO {
    private Integer id;
    private RoleType role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "LogInDTO{" +
                "id=" + id +
                ", role=" + role +
                '}';
    }
}

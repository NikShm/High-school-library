package com.HighSchoolLibrary.entities.users;


import com.HighSchoolLibrary.dto.usersDTO.AdministratorDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/*
@author Микола
@project High-school-library
@class Administrator
@version 1.0.0
@since 27.10.2022 - 12.04
*/
@Entity
@Table(name = "administrator")
public class Administrator extends User{
    @Column(name="degree", length = 128, nullable = false)
    private String degree;

    public Administrator() {
    }

    public Administrator(AdministratorDTO dto) {
        super(dto);
        this.degree = dto.getDegree();
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}

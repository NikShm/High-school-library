package com.HighSchoolLibrary.entities.users;


import com.HighSchoolLibrary.dto.usersDTO.LibrarianDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/*
@author Микола
@project High-school-library
@class Librarian
@version 1.0.0
@since 27.10.2022 - 12.01
*/
@Entity
@Table(name = "librarian")
public class Librarian extends User{
    @Column(name="position", length = 32, nullable = false)
    private String position;

    public Librarian() {
    }

    public Librarian(LibrarianDTO dto) {
        super(dto);
        this.position = dto.getPosition();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}

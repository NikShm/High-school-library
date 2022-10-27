package com.HighSchoolLibrary.dto.usersDTO;


import com.HighSchoolLibrary.entities.users.Librarian;

/*
@author Микола
@project High-school-library
@class LibrarianDTO
@version 1.0.0
@since 27.10.2022 - 12.10
*/
public class LibrarianDTO extends UserDTO{

    private String position;

    public LibrarianDTO() {
    }

    public LibrarianDTO(Librarian entity) {
        super(entity);
        this.position = entity.getPosition();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}

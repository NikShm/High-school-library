package com.HighSchoolLibrary.dto.usersDTO;


import com.HighSchoolLibrary.entities.users.Administrator;

/*
@author Микола
@project High-school-library
@class AdministratorDTO
@version 1.0.0
@since 27.10.2022 - 12.10
*/
public class AdministratorDTO extends UserDTO{

    private String degree;

    public AdministratorDTO() {
    }

    public AdministratorDTO(Administrator entity) {
        super(entity);
        this.degree = entity.getDegree();
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}

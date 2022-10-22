package com.HighSchoolLibrary.dto;

import com.HighSchoolLibrary.entities.Teacher;
import com.HighSchoolLibrary.entities.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/*
@author Микола
@project FreshBeauty
@class TeacherDTO
@version 1.0.0
@since 04.07.2022 - 17.04
*/
@ApiModel(description = "The class that represents the teacher",parent = User.class, value = "Teacher", discriminator = "id")
public class TeacherDTO extends UserDTO{
    @ApiModelProperty(allowableValues = "128", value = "the cathedra where this teacher teaches", readOnly = true, dataType = "String")
    private String cathedra;
    @ApiModelProperty(allowableValues = "32", value = "The academic degree of this teacher", readOnly = true, dataType = "String")
    private String degree;
    @ApiModelProperty(allowableValues = "32", value = "The rang of this teacher", readOnly = true, dataType = "String")
    private String rank;

    public TeacherDTO() {
    }

    public TeacherDTO(Teacher teacher) {
        super(teacher);
        this.cathedra = teacher.getCathedra();
        this.degree = teacher.getDegree();
        this.rank = teacher.getRank();
    }

    public String getCathedra() {
        return cathedra;
    }

    public void setCathedra(String cathedra) {
        this.cathedra = cathedra;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "TeacherDTO{" +
                "cathedra='" + cathedra + '\'' +
                ", degree='" + degree + '\'' +
                ", rank='" + rank + '\'' +
                '}';
    }
}

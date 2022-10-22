package com.HighSchoolLibrary.entities;


import com.HighSchoolLibrary.dto.TeacherDTO;
import com.HighSchoolLibrary.dto.UserDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/*
@author Микола
@project High_school_library
@class Student
@version 1.0.0
@since 01.08.2022 - 16.35
*/
@Entity
@Table(name = "teacher")
@ApiModel(description = "The class that represents the teacher",parent = User.class, value = "Teacher", discriminator = "id")
public class Teacher extends User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(allowableValues = "128", value = "The discriminator field.", readOnly = true, dataType = "Integer")
    private Integer id;
    @Column(name="cathedra",length=128, nullable = false)
    @ApiModelProperty(allowableValues = "128", value = "the cathedra where this teacher teaches", readOnly = true, dataType = "String")
    private String cathedra;
    @Column(name="degree", length = 32, nullable = false)
    @ApiModelProperty(allowableValues = "32", value = "The academic degree of this teacher", readOnly = true, dataType = "String")
    private String degree;
    @Column(name="rank", length = 32, nullable = false)
    @ApiModelProperty(allowableValues = "32", value = "The rang of this teacher", readOnly = true, dataType = "String")
    private String rank;

    public Teacher() {
    }

    public Teacher(TeacherDTO dto) {
        super(dto);
        this.id = dto.getId();
        this.cathedra = dto.getCathedra();
        this.degree = dto.getDegree();
        this.rank = dto.getRank();
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
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

    public void setRank(String subgroup) {
        this.rank = subgroup;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", cathedra=" + cathedra +
                ", degree='" + degree + '\'' +
                ", subgroup='" + rank + '\'' +
                '}';
    }
}
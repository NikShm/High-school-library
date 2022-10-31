package com.HighSchoolLibrary.entities.users;


import com.HighSchoolLibrary.dto.usersDTO.StudentDTO;
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
@Table(name="student")
@ApiModel(description = "The class that represents the student",parent = User.class, value = "Student", discriminator = "id")
public class Student extends User {
    @Id
    @Column(name = "id", nullable = false)
    @ApiModelProperty(allowableValues = "128", value = "The discriminator field.", readOnly = true, dataType = "Integer")
    private Integer id;
    @Column(name="faculty", nullable = false)
    @ApiModelProperty(allowableValues = "128", value = "The faculty in which student study.", readOnly = true, dataType = "String")
    private String faculty;
    @Column(name="class", length = 32, nullable = false)
    @ApiModelProperty(allowableValues = "32", value = "The group in which student study.", readOnly = true, dataType = "String")
    private String group;
    @Column(name="subgroup", length = 32, nullable = false)
    @ApiModelProperty(allowableValues = "32", value = "The subgroup in which student study.", readOnly = true, dataType = "String")
    private String subgroup;

    public Student() {
    }

    public Student(StudentDTO dto) {
        super(dto);
        this.id = dto.getId();
        this.faculty = dto.getFaculty();
        this.group = dto.getGroup();
        this.subgroup = dto.getSubgroup();
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getSubgroup() {
        return subgroup;
    }

    public void setSubgroup(String subgroup) {
        this.subgroup = subgroup;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", faculty=" + faculty +
                ", group='" + group + '\'' +
                ", subgroup='" + subgroup + '\'' +
                '}';
    }
}
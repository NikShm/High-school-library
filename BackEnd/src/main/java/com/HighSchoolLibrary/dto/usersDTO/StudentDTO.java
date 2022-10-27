package com.HighSchoolLibrary.dto.usersDTO;

import com.HighSchoolLibrary.entities.users.Student;
import com.HighSchoolLibrary.entities.users.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Yuliana
 * @version 1.0.0
 * @project FreshBeauty
 * @class StudentDTO
 * @since 7/7/2022 - 08.27
 **/
@ApiModel(description = "The class that represents the student",parent = User.class, value = "Student", discriminator = "id")
public class StudentDTO extends UserDTO{
    @ApiModelProperty(allowableValues = "128", value = "The faculty in which student study.", readOnly = true, dataType = "String")
    private String faculty;
    @ApiModelProperty(allowableValues = "32", value = "The group in which student study.", readOnly = true, dataType = "String")
    private String group;
    @ApiModelProperty(allowableValues = "32", value = "The subgroup in which student study.", readOnly = true, dataType = "String")
    private String subgroup;

    public StudentDTO() {
    }

    public StudentDTO(Student student) {
        super(student);
        this.faculty = student.getFaculty();
        this.group = student.getGroup();
        this.subgroup = student.getSubgroup();
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
        return "StudentDTO{" +
                "faculty='" + faculty + '\'' +
                ", group='" + group + '\'' +
                ", subgroup='" + subgroup + '\'' +
                '}';
    }
}

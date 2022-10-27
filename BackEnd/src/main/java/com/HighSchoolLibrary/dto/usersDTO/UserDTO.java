package com.HighSchoolLibrary.dto.usersDTO;


import com.HighSchoolLibrary.entities.users.Student;
import com.HighSchoolLibrary.entities.users.Teacher;
import com.HighSchoolLibrary.entities.users.User;
import com.HighSchoolLibrary.enums.RoleType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/*
@author Микола
@project FreshBeauty
@class UserDTO
@version 1.0.0
@since 01.08.2022 - 16.44
*/
@ApiModel(description = "Abstract user class", subTypes = {Student.class, Teacher.class}, value = "User", discriminator = "id")
public  class UserDTO {

    @ApiModelProperty(allowableValues = "128", value = "The discriminator field.", readOnly = true, dataType = "Integer")
    private Integer id;
    @ApiModelProperty(allowableValues = "128", value = "The users name.", readOnly = true, dataType = "String")
    private String name;
    @ApiModelProperty(allowableValues = "128", value = "The users surname.", readOnly = true, dataType = "String")
    private String surname;
    @ApiModelProperty(allowableValues = "128", value = "The users login. Use to authorize", readOnly = true, dataType = "String")
    private String login;
    @ApiModelProperty(allowableValues = "32", value = "The users password. Use to authorize", readOnly = true, dataType = "String")
    private String password;
    @ApiModelProperty(allowableValues = "128", value = "The users access rights.", readOnly = true, dataType = "RoleType")
    private RoleType role;
    @ApiModelProperty(allowableValues = "128", value = "The users access rights.", readOnly = true, dataType = "RoleType")
    private String type;
    @ApiModelProperty( value = "The time when users was been created.", readOnly = true, dataType = "LocalDateTime")
    private String createdAt;

    public UserDTO() {
    }

    public UserDTO(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.surname = entity.getSurname();
        this.login = entity.getLogin();
        this.password = entity.getPassword();
        this.role = entity.getRole();
        this.type = entity.getType();
        this.createdAt = entity.getCreatedAt().toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "Id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", type='" + type + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}

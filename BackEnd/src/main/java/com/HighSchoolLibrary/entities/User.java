package com.HighSchoolLibrary.entities;

import com.HighSchoolLibrary.dto.UserDTO;
import com.HighSchoolLibrary.enums.RoleType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@TypeDef(name = "enum", typeClass = PostgreSQLEnumType.class)
@ApiModel(description = "Abstract user class", subTypes = {Student.class, Teacher.class}, value = "User", discriminator = "id")
public abstract class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(allowableValues = "128", value = "The discriminator field.", readOnly = true, dataType = "Integer")
    private Integer id;
    @Column(name = "name", length = 128, nullable = false)
    @ApiModelProperty(allowableValues = "128", value = "The users name.", readOnly = true, dataType = "String")
    private String name;
    @Column(name = "surname", length = 128, nullable = false)
    @ApiModelProperty(allowableValues = "128", value = "The users surname.", readOnly = true, dataType = "String")
    private String surname;
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    @Type(type = "enum")
    @ApiModelProperty(allowableValues = "128", value = "The users access rights.", readOnly = true, dataType = "RoleType", position = 5)
    private RoleType role;
    @Column(name = "login", length = 128, nullable = false)
    @ApiModelProperty(allowableValues = "128", value = "The users login. Use to authorize", readOnly = true, dataType = "String", position = 3)
    private String login;
    @Column(name = "password", length = 32, nullable = false)
    @ApiModelProperty(allowableValues = "32", value = "The users password. Use to authorize", readOnly = true, dataType = "String", position = 4)
    private String password;
    @Column(name = "type", length = 32, nullable = false)
    @ApiModelProperty(allowableValues = "128", value = "The users access rights.", readOnly = true, dataType = "RoleType", position = 6)
    private String type;
    @Column(name = "createdat", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSSSSS][.SSSSS][.SSSS][.SSS][.SS][.S]")
    @ApiModelProperty( value = "The time when users was been created.", readOnly = true, dataType = "LocalDateTime", position = 7)
    private LocalDateTime createdAt;

    public User() {
    }

    public User(UserDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.surname = dto.getSurname();
        this.role = dto.getRole();
        this.login = dto.getLogin();
        this.password = dto.getPassword();
        this.type = dto.getType();
        this.createdAt = LocalDateTime.parse(dto.getCreatedAt(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm:ss[.SSSSSS]"));

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

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = LocalDateTime.parse(createdAt,
                DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm:ss[.SSSSSS]"));
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", role=" + role +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}

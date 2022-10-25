package com.HighSchoolLibrary.entities;


import com.HighSchoolLibrary.enums.RoleType;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/*
@author Микола
@project High_school_library
@class Author
@version 1.0.0
@since 05.09.2022 - 18.32
*/
@Entity
@Table(name = "author")
public class Author {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", length = 128, nullable = false)
    private String name;
    @Column(name="createdat", nullable = false)
    private LocalDateTime createdAt;

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", surname='" + name + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}

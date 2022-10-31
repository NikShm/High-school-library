package com.HighSchoolLibrary.entities;

import com.HighSchoolLibrary.enums.CategoryType;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/*
@author Микола
@project High_school_library
@class Book
@version 1.0.0
@since 05.09.2022 - 18.30
*/

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", length = 128, nullable = false)
    private String name;
    @Column(name = "description", length = 128, nullable = false)
    private String description;
    @Column(name="price", nullable = false)
    private Integer price;
    @Enumerated(EnumType.STRING)
    @Type(type = "enum")
    @Column(name="category", nullable = false)
    private CategoryType category;
    @Column(name="count", nullable = false)
    private Integer count;
    @Column(name="createdat", nullable = false)
    private LocalDateTime createdAt;
    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "bookid"),
            inverseJoinColumns = @JoinColumn(name = "authorid"))
    private List<Author> authorList;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public CategoryType getCategory() {
        return category;
    }

    public void setCategory(CategoryType category) {
        this.category = category;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", count=" + count +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}

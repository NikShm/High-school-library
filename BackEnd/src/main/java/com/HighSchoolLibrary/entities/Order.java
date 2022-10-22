package com.HighSchoolLibrary.entities;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.time.LocalDateTime;

/*
@author Микола
@project High_school_library
@class Order
@version 1.0.0
@since 05.09.2022 - 22.33
*/
@Document(collection = "order")
public class Order {
    @Id
    private String id;
    @Field(value = "id_user")
    private Integer idUser;
    @Field(value = "id_book")
    private Integer idBook;
    @Field(value = "order_date")
    private LocalDateTime orderDate;
    @Field(value = "date_of_issue")
    private LocalDateTime dateOfIssue;
    @Field(value = "return_date")
    private LocalDateTime returnDate;
    @Field(value = "status")
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdBook() {
        return idBook;
    }

    public void setIdBook(Integer idBook) {
        this.idBook = idBook;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDateTime dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", idUser=" + idUser +
                ", idBook=" + idBook +
                ", orderDate='" + orderDate + '\'' +
                ", dateOfIssue='" + dateOfIssue + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

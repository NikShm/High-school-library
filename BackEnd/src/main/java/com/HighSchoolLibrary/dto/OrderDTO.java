package com.HighSchoolLibrary.dto;


import com.HighSchoolLibrary.entities.Order;

import java.time.LocalDateTime;

/*
@author Микола
@project High_school_library
@class OrderDTO
@version 1.0.0
@since 05.09.2022 - 22.44
*/
public class OrderDTO {
    private String id;
    private Integer idUser;
    private BookDTO book;
    private LocalDateTime orderDate;
    private LocalDateTime dateOfIssue;
    private LocalDateTime returnDate;
    private String status;

    public OrderDTO() {
    }

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.idUser = order.getIdUser();
        this.orderDate = order.getOrderDate();
        this.dateOfIssue = order.getDateOfIssue();
        this.returnDate = order.getReturnDate();
        this.status = order.getStatus();
    }

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

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
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
        return "OrderDTO{" +
                "id='" + id + '\'' +
                ", idUser=" + idUser +
                ", idBook=" + book +
                ", orderDate=" + orderDate +
                ", dateOfIssue=" + dateOfIssue +
                ", returnDate=" + returnDate +
                ", status='" + status + '\'' +
                '}';
    }
}

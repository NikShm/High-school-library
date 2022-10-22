package com.HighSchoolLibrary.entities;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

/*
@author Микола
@project High_school_library
@class PenaltyService
@version 1.0.0
@since 18.08.2022 - 16.28
*/
@Document(collection = "penalty")
public class Penalty {
    @Id
    private String id;
    @Field(value = "currency")
    private String currency;
    @Field(value = "description")
    private String description;
    @Field(value = "price")
    private Integer price;
    @Field(value = "status")
    private String status;
    @Field(value = "id_accuser")
    private Integer idAccuser;
    @Field(value = "id_penalty_kicker")
    private Integer idPenaltyKicker;
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIdAccuser() {
        return idAccuser;
    }

    public void setIdAccuser(Integer idAccuser) {
        this.idAccuser = idAccuser;
    }

    public Integer getIdPenaltyKicker() {
        return idPenaltyKicker;
    }

    public void setIdPenaltyKicker(Integer idPenaltyKicker) {
        this.idPenaltyKicker = idPenaltyKicker;
    }
}

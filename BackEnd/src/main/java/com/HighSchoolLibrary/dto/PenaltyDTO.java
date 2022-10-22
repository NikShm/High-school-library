package com.HighSchoolLibrary.dto;


import com.HighSchoolLibrary.entities.Penalty;
import org.springframework.data.mongodb.core.mapping.Field;

/*
@author Микола
@project High_school_library
@class PenaltyDTO
@version 1.0.0
@since 01.09.2022 - 15.05
*/
public class PenaltyDTO {
    private String id;
    private String currency;
    private String description;
    private Integer price;
    private String status;
    private Integer idAccuser;
    private Integer idPenaltyKicker;

    public PenaltyDTO(Penalty penalty) {
        this.id = penalty.getId();
        this.currency = penalty.getCurrency();
        this.description = penalty.getDescription();
        this.price = penalty.getPrice();
        this.status = penalty.getStatus();
        this.idAccuser = penalty.getIdAccuser();
        this.idPenaltyKicker = penalty.getIdPenaltyKicker();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

package com.HighSchoolLibrary.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/*
@author Микола
@project FreshBeauty
@class PageDTO
@version 1.0.0
@since 13.07.2022 - 19.28
*/
@ApiModel(description = "Page creation options class", value = "Page")
public class PageDTO<T> {
    @ApiModelProperty(value = "List of elements of a given page.", readOnly = true, dataType = "int")
    List<T> content;
    @ApiModelProperty(value = "The total number of elements in the database.", readOnly = true, dataType = "int")
    long totalItem;

    public long getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(long totalItem) {
        this.totalItem = totalItem;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}

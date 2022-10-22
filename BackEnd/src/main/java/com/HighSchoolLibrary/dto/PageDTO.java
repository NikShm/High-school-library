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
    @ApiModelProperty(value = "Total number of pages.", readOnly = true, dataType = "int")
    int pageCount;
    @ApiModelProperty(value = "Page number to be returned.", readOnly = true, dataType = "int")
    int page;
    @ApiModelProperty(value = "The size of the returned page.", readOnly = true, dataType = "int")
    int pageSize;
    @ApiModelProperty(value = "The total number of elements in the database.", readOnly = true, dataType = "int")
    long totalItem;

    public long getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(long totalItem) {
        this.totalItem = totalItem;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}

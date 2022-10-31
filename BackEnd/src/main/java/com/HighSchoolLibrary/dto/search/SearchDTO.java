package com.HighSchoolLibrary.dto.search;

import com.HighSchoolLibrary.enums.SortDirection;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/*
@author Микола
@project FreshBeauty
@class SearchDTO
@version 1.0.0
@since 13.07.2022 - 19.34
*/
@ApiModel(description = "Page creation options class", value = "Search")
public class SearchDTO<T> {
    @ApiModelProperty(value = "The field by which sorting is performed.", readOnly = true, dataType = "String")
    private String sortField;
    @ApiModelProperty(value = "The type of sort.", readOnly = true, dataType = "SortDirection")
    private SortDirection sortDirection;
    @ApiModelProperty(value = "Page number to be returned.", readOnly = true, dataType = "String")
    private Integer page;
    @ApiModelProperty(value = "The size of the returned page.", readOnly = true, dataType = "String")
    private Integer pageSize;
    private T searchPattern;

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public SortDirection getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(SortDirection sortDirection) {
        this.sortDirection = sortDirection;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public T getSearchPattern() {
        return searchPattern;
    }

    public void setSearchPattern(T searchPattern) {
        this.searchPattern = searchPattern;
    }

    @Override
    public String toString() {
        return "SearchDTO{" +
                "sortField='" + sortField + '\'' +
                ", sortDirection=" + sortDirection +
                ", page=" + page +
                ", pageSize=" + pageSize +
                ", searchPattern=" + searchPattern.toString() +
                '}';
    }
}

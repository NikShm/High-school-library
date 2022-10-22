package com.HighSchoolLibrary.dto;

import com.HighSchoolLibrary.entities.Student;
import com.HighSchoolLibrary.entities.Teacher;
import com.HighSchoolLibrary.entities.User;
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
public class SearchDTO {
    @ApiModelProperty(value = "The line in which the user is searched, in the name and surname fields.", readOnly = true, dataType = "String")
    private String search;
    @ApiModelProperty(value = "The field by which sorting is performed.", readOnly = true, dataType = "String")
    private String sortField;
    @ApiModelProperty(value = "The type of sort.", readOnly = true, dataType = "SortDirection")
    private SortDirection sortDirection;
    @ApiModelProperty(value = "Page number to be returned.", readOnly = true, dataType = "String")
    private int page;
    @ApiModelProperty(value = "The size of the returned page.", readOnly = true, dataType = "String")
    private int pageSize;


    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "SearchDTO{" +
                "search='" + search + '\'' +
                ", sortField='" + sortField + '\'' +
                ", sortDirection=" + sortDirection +
                ", page=" + page +
                ", pageSize=" + pageSize +
                '}';
    }
}

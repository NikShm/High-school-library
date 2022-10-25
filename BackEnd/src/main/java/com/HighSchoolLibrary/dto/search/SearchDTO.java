package com.HighSchoolLibrary.dto.search;

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
    private Integer page;
    @ApiModelProperty(value = "The size of the returned page.", readOnly = true, dataType = "String")
    private Integer pageSize;

    public SearchDTO() {
    }

    public SearchDTO(String search, String sortField, SortDirection sortDirection, Integer page, Integer pageSize) {
        this.search = search;
        this.sortField = sortField;
        this.sortDirection = sortDirection;
        this.page = page;
        this.pageSize = pageSize;
    }

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

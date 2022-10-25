package com.HighSchoolLibrary.utils;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.Locale;

import com.HighSchoolLibrary.enums.SortDirection;
import org.springframework.data.domain.Sort;

/*
@author Микола
@project FreshBeauty
@class QueryHelper
@version 1.0.0
@since 14.07.2022 - 22.54
*/
public class QueryHelper {
    private QueryHelper() {
        throw new UnsupportedOperationException();
    }

    public static Predicate ilike(Path<String> path, CriteriaBuilder criteriaBuilder, String value) {
        return criteriaBuilder.like(criteriaBuilder.lower(path), "%" + value.toLowerCase(Locale.ROOT) + "%");
    }

    public static Sort getSort(SortDirection sortDirection, String sortField){
        Sort sort = Sort.by(sortField);
        if (sortDirection == SortDirection.DESC) {
            sort = sort.descending();
        }
        return sort;
    }

}

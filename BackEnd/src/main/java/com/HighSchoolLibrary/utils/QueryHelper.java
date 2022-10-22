package com.HighSchoolLibrary.utils;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.Locale;

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
}

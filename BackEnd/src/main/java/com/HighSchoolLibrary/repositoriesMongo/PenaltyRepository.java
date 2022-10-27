package com.HighSchoolLibrary.repositoriesMongo;/*
@author Микола
@project High_school_library
@class PenaltyRepository
@version 1.0.0
@since 18.08.2022 - 16.27
*/

import com.HighSchoolLibrary.entities.Penalty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

@EnableMongoRepositories(basePackages = "com.HighSchoolLibrary.repositoriesMongo")
public interface PenaltyRepository extends MongoRepository<Penalty, String> {
    List<Penalty> findAllByIdPenaltyKickerAndIdBookAndStatus(Integer idPenaltyKicker,Integer idBook, String status);
}

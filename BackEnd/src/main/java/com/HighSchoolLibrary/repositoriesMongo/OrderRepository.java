package com.HighSchoolLibrary.repositoriesMongo;/*
@author Микола
@project High_school_library
@class PenaltyRepository
@version 1.0.0
@since 18.08.2022 - 16.27
*/

import com.HighSchoolLibrary.dto.BookMap;
import com.HighSchoolLibrary.entities.Order;
import com.HighSchoolLibrary.entities.Penalty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findAllByIdUserAndIdBookAndStatus(Integer idAccuser, Integer idBook, String status);
}

package com.HighSchoolLibrary.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.sql.DataSource;

/*
@author Микола
@project FreshBeauty
@class DataSourceConfig
@version 1.0.0
@since 04.07.2022 - 16.56
*/
@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource getDataSource(@Value("${datasource.url}") String url,
                                    @Value("${datasource.driverClassName}") String driverClassName,
                                    @Value("${datasource.username}") String username,
                                    @Value("${datasource.password}") String password
    ) {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driverClassName);
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();
    }
}

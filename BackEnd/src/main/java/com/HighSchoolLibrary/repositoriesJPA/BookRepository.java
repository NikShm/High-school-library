package com.HighSchoolLibrary.repositoriesJPA;
import com.HighSchoolLibrary.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*
@author Микола
@project High_school_library
@class BookRepository
@version 1.0.0
@since 05.09.2022 - 18.53
*/
@Repository
@EnableJpaRepositories("com.HighSchoolLibrary.repositoriesJPA")
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAllById(Integer id);
}

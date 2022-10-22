package com.HighSchoolLibrary.repositoriesJPA;
import com.HighSchoolLibrary.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
/*
@author Микола
@project High_school_library
@class AuthorRepository
@version 1.0.0
@since 05.09.2022 - 18.52
*/
@Repository
@EnableJpaRepositories("com.HighSchoolLibrary.repositoriesJPA")
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}

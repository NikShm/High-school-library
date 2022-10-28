package com.HighSchoolLibrary.repositoriesJPA;/*
@author Микола
@project High-school-library
@class StudentRepository
@version 1.0.0
@since 28.10.2022 - 15.23
*/

import com.HighSchoolLibrary.entities.users.Student;
import com.HighSchoolLibrary.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.HighSchoolLibrary.repositoriesJPA")
public interface StudentRepository extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student> {
}

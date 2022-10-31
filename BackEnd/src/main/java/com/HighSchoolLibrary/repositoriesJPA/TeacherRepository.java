package com.HighSchoolLibrary.repositoriesJPA;/*
@author Микола
@project High-school-library
@class StudentRepository
@version 1.0.0
@since 28.10.2022 - 15.23
*/

import com.HighSchoolLibrary.entities.users.Student;
import com.HighSchoolLibrary.entities.users.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer>, JpaSpecificationExecutor<Teacher> {
}

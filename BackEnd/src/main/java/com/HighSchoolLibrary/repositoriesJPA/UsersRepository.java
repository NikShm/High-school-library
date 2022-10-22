package com.HighSchoolLibrary.repositoriesJPA;

import com.HighSchoolLibrary.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Yuliana
 * @version 1.0.0
 * @project FreshBeauty
 * @class StudentRepository
 * @since 7/6/2022 - 20.22
 **/
@EnableJpaRepositories("com.HighSchoolLibrary.repositoriesJPA")
public interface UsersRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User>{
}

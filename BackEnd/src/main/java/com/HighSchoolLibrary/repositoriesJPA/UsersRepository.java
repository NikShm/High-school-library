package com.HighSchoolLibrary.repositoriesJPA;

import com.HighSchoolLibrary.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author Yuliana
 * @version 1.0.0
 * @project FreshBeauty
 * @class StudentRepository
 * @since 7/6/2022 - 20.22
 **/
@Repository
public interface UsersRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    User findByLoginAndPassword(String login, String password);
}

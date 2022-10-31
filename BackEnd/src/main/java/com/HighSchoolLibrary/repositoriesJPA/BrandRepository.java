package com.HighSchoolLibrary.repositoriesJPA;

import com.HighSchoolLibrary.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<User, Integer> {

}

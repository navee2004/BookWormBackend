package com.springproject.demo.repository;

import com.springproject.demo.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    
    // Custom query to find users by their email
    @Query("SELECT u FROM UserModel u WHERE u.email = ?1")
    UserModel findByEmail(String email);
}

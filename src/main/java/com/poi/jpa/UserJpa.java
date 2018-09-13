package com.poi.jpa;

import com.poi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserJpa extends JpaRepository<User,Integer> {

    @Query(value = "select uid,username,password,email,u_create from user where username = :uname",nativeQuery = true)
    User findByUsername(@Param("uname") String username);
}

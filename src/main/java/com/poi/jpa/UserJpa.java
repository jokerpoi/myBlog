package com.poi.jpa;

import com.poi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpa extends JpaRepository<User,Integer> {
}

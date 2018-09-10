package com.poi.jpa;

import com.poi.entity.Contents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentJpa extends JpaRepository<Contents,Integer> {
}

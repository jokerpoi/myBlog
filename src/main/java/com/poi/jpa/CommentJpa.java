package com.poi.jpa;

import com.poi.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpa extends JpaRepository<Comments,Integer> {
}

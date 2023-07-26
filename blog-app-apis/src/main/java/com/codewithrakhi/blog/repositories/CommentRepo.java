package com.codewithrakhi.blog.repositories;

import com.codewithrakhi.blog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
}

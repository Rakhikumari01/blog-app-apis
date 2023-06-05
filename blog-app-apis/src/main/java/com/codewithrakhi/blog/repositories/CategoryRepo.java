package com.codewithrakhi.blog.repositories;

import com.codewithrakhi.blog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepo extends JpaRepository<Category, Integer> {
}

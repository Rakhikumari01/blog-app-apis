package com.codewithrakhi.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codewithrakhi.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {


}

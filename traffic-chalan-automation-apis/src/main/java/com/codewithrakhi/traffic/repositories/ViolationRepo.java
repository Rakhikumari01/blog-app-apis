package com.codewithrakhi.traffic.repositories;

import com.codewithrakhi.traffic.entity.User;
import com.codewithrakhi.traffic.entity.Violation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViolationRepo extends JpaRepository<Violation, Integer> {


    List<Violation> findByUser(User user);
}

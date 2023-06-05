package com.codewithrakhi.traffic.repositories;


import com.codewithrakhi.traffic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo  extends JpaRepository<User, Integer> {

    /* @Query("Select u from User where u.vehicle_no equals:v")
     List<User> getUserByvehicle_no(@Param("v") Integer vehicle_no);*/

     List<User> getByVehicleNoLike(Integer vehicleNo);


}

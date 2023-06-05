package com.codewithrakhi.traffic.services;

import com.codewithrakhi.traffic.payload.UserDto;

import java.util.List;

public interface UserService {


     UserDto  createUser(UserDto user);

     UserDto updateUser(UserDto user, Integer userId);

     UserDto getUserById(Integer userId);

     List<UserDto> getAllUser();


     List<UserDto> getUserByvehicle_no(Integer vehicle_no);

     void deleteUser(Integer userId);
}

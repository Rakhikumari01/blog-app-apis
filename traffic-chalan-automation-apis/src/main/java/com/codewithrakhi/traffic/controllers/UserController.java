package com.codewithrakhi.traffic.controllers;


import com.codewithrakhi.traffic.payload.ApiResponse;
import com.codewithrakhi.traffic.payload.UserDto;
import com.codewithrakhi.traffic.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

 @Autowired
 private UserService userService;

 //post-create user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {

        UserDto createUserDto = this.userService.createUser(userDto);

        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    //put-update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable("userId") Integer uid)
    {
        UserDto updateUser = this.userService.updateUser(userDto, uid);
        return ResponseEntity.ok(updateUser);

    }

     //delete
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid)
    {
        this.userService.deleteUser((uid));
        return new ResponseEntity<ApiResponse> (new ApiResponse("User deleetd succesfully",true),HttpStatus.OK);
    }

//get all user
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){

        return ResponseEntity.ok(this.userService.getAllUser());
    }

    //get single user
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId)
    {
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }

    //get user vehicle_no
    @GetMapping("vehicle/{vehicle_no}")
    public ResponseEntity<List<UserDto>> getUserByvehicle(@PathVariable Integer vehicle_no)
    {
        return ResponseEntity.ok(this.userService.getUserByvehicle_no(vehicle_no));
    }

}

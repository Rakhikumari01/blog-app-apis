package com.codewithrakhi.blog.controllers;


import com.codewithrakhi.blog.payloads.ApiResponse;
import com.codewithrakhi.blog.payloads.UserDto;
import com.codewithrakhi.blog.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // POST - CREATE USER
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {

        UserDto createUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    //PUT- UPDATE USER
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer uid) {

        UserDto updatedUser = this.userService.updateUser(userDto, uid);
        return ResponseEntity.ok(updatedUser);
    }

    //DELETE - DELETE USER
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid) {
        this.userService.deleteUser(uid);
        return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted Successfully", true), HttpStatus.OK);
    }

    // get all user- userget
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers() {

        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    // get single user- userget
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUsers(@PathVariable Integer userId) {

        return ResponseEntity.ok(this.userService.getUserById(userId));
    }


}

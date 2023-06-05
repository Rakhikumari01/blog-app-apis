package com.codewithrakhi.traffic.services.Seviceimpl;

import com.codewithrakhi.traffic.entity.User;
import com.codewithrakhi.traffic.exception.ResourceNotFoundException;
import com.codewithrakhi.traffic.payload.UserDto;
import com.codewithrakhi.traffic.repositories.UserRepo;
import com.codewithrakhi.traffic.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepo userRepo;

    @Autowired
   private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto)
    {
        User user = this.dtoToUser(userDto);

        User savedUser = this.userRepo.save(user);

        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId)
    {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setDepartment(userDto.getDepartment());
        user.setVehicleNo(userDto.getVehicle_no());
        user.setPassword(userDto.getPassword());


        User updatedUser = this.userRepo.save(user);
        UserDto userDto1 = this.userToDto(updatedUser);

        return userDto1;
    }

    @Override
    public UserDto getUserById( Integer userId)
    {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));

        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUser()
    {
       List<User> users = this.userRepo.findAll();
       List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());

        return userDtos;
    }

    @Override
    public List<UserDto> getUserByvehicle_no(Integer vehicleNo) {

        List<User> users = this.userRepo.getByVehicleNoLike(vehicleNo);
        List<UserDto> userDtos = users.stream().map(this::userToDto).collect(Collectors.toList());
        return userDtos;

    }


    @Override
    public void deleteUser(Integer userId)
    {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));

        this.userRepo.delete(user);
    }

    private User dtoToUser(UserDto userDto)
    {
        User user= this.modelMapper.map(userDto,User.class);
        return user;

    }

    private UserDto userToDto(User user)
    {
        UserDto userDto = this.modelMapper.map(user,UserDto.class);
        return userDto;
    }
}

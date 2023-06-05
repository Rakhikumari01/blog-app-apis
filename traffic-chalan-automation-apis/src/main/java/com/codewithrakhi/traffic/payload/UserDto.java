package com.codewithrakhi.traffic.payload;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class UserDto {

    //user dto is used to transfer data to service
    private int tata_user_id;

@NotEmpty
@Size(min=4,message="name must be of 4 characters")
private String name;

@Email(message = "Email id is not valid")
private String email;

@NotEmpty
@Size(min=4,max=10, message = "password should contain min of 4 and maximum of 4 characters")
private String password;

@NotEmpty
private String department;

@NotEmpty
private int vehicle_no;


}

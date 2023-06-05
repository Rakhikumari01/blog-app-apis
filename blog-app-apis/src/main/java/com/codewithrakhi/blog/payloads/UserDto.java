package com.codewithrakhi.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

/*used for data transfer*/

    private int id;

    @NotEmpty
    @Size(min =4, message ="username must be a min of 4 characters")
    private String name;

    @Email(message= "email addresss is not valid!!")
    private String email;

    @NotEmpty
    @Size(min=3, max=10,message ="password should be a min of 3 characters and minimum of 10 characters")
    private String password;

    private String about;
}

// user dto is used to transfer data in userservice
package com.codewithrakhi.traffic.payload;

import com.codewithrakhi.traffic.entity.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor


public class ViolationDto {

 private int violationId;

 @NotEmpty()
 @Size(min=50,message = "violation title should not exceed 50 characters")
 private String violationTitle;

 @NotEmpty
 private String violationImage;

 private Date addedDate;

 private UserDto user;

}

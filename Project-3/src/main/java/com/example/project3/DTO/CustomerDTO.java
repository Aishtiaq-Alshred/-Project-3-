package com.example.project3.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CustomerDTO {

//    @NotNull(message = "user_id must not be null")
    private Integer user_id;
//    @NotNull(message = "phone number must not be null")
//    @Pattern(regexp = "05.[0-9]+",message = "phone number start with 05")
//    @Size(max = 10,min = 10,message = "phone number must be 10 digits" )
    private String phoneNumber;
}

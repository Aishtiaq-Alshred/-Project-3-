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
//    @NotEmpty(message = "username should not be null")
//    @Column(columnDefinition = "varchar(10) not null unique")
    private String username;
//    @NotEmpty(message = "password should not be null")
    private String password;
//    @NotEmpty(message = "name should not be null")
//    @Column(columnDefinition = "varchar(20) not null")
    private String name;
//    @NotNull(message = "email should not be null")
//    @Email(message = "must be valid email")
//    @Column(columnDefinition = "varchar(30) not null unique")
    private String email;
//    @NotNull(message = "user_id must not be null")
    private Integer user_id;
//    @NotNull(message = "phone number must not be null")
//    @Pattern(regexp = "05.[0-9]+",message = "phone number start with 05")
//    @Size(max = 10,min = 10,message = "phone number must be 10 digits" )
    private String phoneNumber;
}

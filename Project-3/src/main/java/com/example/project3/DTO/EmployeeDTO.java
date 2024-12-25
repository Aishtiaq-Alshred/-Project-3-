package com.example.project3.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class EmployeeDTO {
//    @NotEmpty(message = "username should not be null")
//    @Column(columnDefinition = "varchar(10) not null unique")
    private String username;
//
//    @NotEmpty(message = "password should not be null")
    private String password;
//    @NotEmpty(message = "name should not be null")
//    @Column(columnDefinition = "varchar(20) not null")
    private String name;
//    @NotNull(message = "email should not be null")
//    @Email(message = "must be valid email")
//    @Column(columnDefinition = "varchar(30) not null unique")
    private String email;

    @NotNull(message = "position cannot be null")
    private String position;
    @NotNull(message = "salary cannot be null")
    @Positive(message = "salary must be positive")
    private double salary;
}

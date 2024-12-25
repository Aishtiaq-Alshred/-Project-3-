package com.example.project3.DTO;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class AccountDTO {

//    @NotNull(message = "customer id not null")
    private Integer customer_user_id;
//    @NotNull(message = "account number cannot be null")
    private String accountNumber;
//    @NotNull(message = "balance cannot be null")
//    @Positive(message = "balance must be positive")
    private double balance;
//    @AssertFalse(message = "must be false")
    private boolean isActive;
}

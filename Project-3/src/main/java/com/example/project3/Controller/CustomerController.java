package com.example.project3.Controller;

import com.example.project3.ApiResponce.ApiResponse;
import com.example.project3.DTO.CustomerDTO;
import com.example.project3.Model.User;
import com.example.project3.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/get")
    public ResponseEntity getCustomer(){
        return ResponseEntity.status(200).body(customerService.getCustomers());
    }
    @PostMapping("/register")
    public ResponseEntity registerCustomer(@RequestBody @Valid CustomerDTO customerDTO){
        customerService.registerCustomer(customerDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Customer added"));
    }
    @PutMapping("/update")
    public ResponseEntity updateCustomer(@RequestBody @Valid CustomerDTO customerDTO,@AuthenticationPrincipal User user){
        customerService.updateCustomer(customerDTO, user);
        return ResponseEntity.status(200).body(new ApiResponse("Customer update"));
    }
    @DeleteMapping("/delete")
    public ResponseEntity deleteCustomer(@AuthenticationPrincipal User user){
        customerService.deleteCustomer(user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Customer deleted"));
    }
}

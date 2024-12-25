package com.example.project3.Controller;

import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.Model.User;
import com.example.project3.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/get")
    public ResponseEntity getEmployee(){
        return ResponseEntity.status(200).body(employeeService.getEmployee());
    }


    @PostMapping("/register")
    public ResponseEntity registerEmployee(@Valid @RequestBody EmployeeDTO employeeDTO){
        employeeService.registerEmployee(employeeDTO);
        return ResponseEntity.status(200).body("employee added");
    }


    @PutMapping("/update")
    public ResponseEntity updateEmployee(@RequestBody @Valid  EmployeeDTO employeeDTO, @AuthenticationPrincipal User user){
        employeeService.updateEmployee(employeeDTO,user);
        return ResponseEntity.status(200).body("employee update");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Integer id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(200).body("employee delete");
    }
}

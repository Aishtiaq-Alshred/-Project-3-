package com.example.project3.Service;

import com.example.project3.ApiResponce.ApiException;
import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.Model.Employee;
import com.example.project3.Model.User;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {


    private final EmployeeRepository employeeRepository;

    private final AuthRepository authRepository;


    public List<Employee> getEmployee(){
        return employeeRepository.findAll();
    }


    public void registerEmployee(EmployeeDTO employeeDTO){
       User user =new User();

        user.setRole("EMPLOYEE");
        user.setPassword(new BCryptPasswordEncoder().encode(employeeDTO.getPassword()));
        user.setUsername(employeeDTO.getUsername());
        user.setEmail(employeeDTO.getEmail());
        user.setName(employeeDTO.getName());

        Employee employee = new Employee(null, employeeDTO.getPosition(),employeeDTO.getSalary(),user);
        employeeRepository.save(employee);
        authRepository.save(user);

    }

    public void updateEmployee(EmployeeDTO employeeDTO, User user) {

        User user1 = authRepository.findUserByUsername(user.getUsername());
        if (user1 == null) {
            throw new ApiException("User not found");
        }

        Employee employee = employeeRepository.findEmployeeById(user1.getId());
        if (employee == null) {
            throw new ApiException("Employee not found");
        }

        user1.setName(employeeDTO.getName());
        user1.setEmail(employeeDTO.getEmail());

        if (employeeDTO.getPassword() != null && !employeeDTO.getPassword().isEmpty()) {
            String hashPassword = new BCryptPasswordEncoder().encode(employeeDTO.getPassword());
            user1.setPassword(hashPassword);
        }

        employee.setPosition(employeeDTO.getPosition());
        employee.setSalary(employeeDTO.getSalary());

        authRepository.save(user1);
        employeeRepository.save(employee);
    }




    public void deleteEmployee(Integer id) {
        Employee employee = employeeRepository.findEmployeeById(id);
        if (employee == null) {
            throw new ApiException("Employee ID not found");
        }
        User user = employee.getUser();
        if (user != null) {
            authRepository.delete(user);
        }
        employeeRepository.delete(employee);
    }

}

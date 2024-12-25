package com.example.project3.Service;

import com.example.project3.ApiResponce.ApiException;
import com.example.project3.DTO.CustomerDTO;
import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.Model.Customer;
import com.example.project3.Model.Employee;
import com.example.project3.Model.User;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.CustomerRepository;
import com.example.project3.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AuthRepository authRepository;




    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public void registerCustomer(User user , CustomerDTO customerDTO){

        User user1 = new User();
        user1.setUsername(user.getUsername());
        user1.setName(user.getName());
        user1.setPassword(user.getPassword());
        user1.setEmail(user.getEmail());
        user1.setRole("CUSTOMER");

        String hash = new BCryptPasswordEncoder().encode(user1.getPassword());

        Customer customer = new Customer();
        customer.setId(customerDTO.getUser_id());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());


        user1.setPassword(hash);
        user1.setCustomer(customer);
        customer.setUser(user1);

        customerRepository.save(customer);
        authRepository.save(user1);
    }

//    public void updateCustomer(CustomerDTO customerDTO, User user) {
//        User user1 = authRepository.findUserByUsername(user.getUsername());
//        if (user1 == null) {
//            throw new ApiException("User not found");
//        }
//
//        Customer customer = customerRepository.findCustomerById(user1.getId());
//        if (customer == null) {
//            throw new ApiException("Customer not found");
//        }
//
//        user1.setName(customerDTO.getName());
//        user1.setEmail(customerDTO.getEmail());
//
//        if (customerDTO.getPassword() != null && !customerDTO.getPassword().isEmpty()) {
//            user1.setPassword(new BCryptPasswordEncoder().encode(customerDTO.getPassword()));
//        }
//
//        customer.setPhoneNumber(customerDTO.getPhoneNumber());
//
//        authRepository.save(user1);
//        customerRepository.save(customer);
//    }

    public void deleteCustomer(Integer id) {
        Customer customer = customerRepository.findCustomerById(id);
        if (customer == null) {
            throw new ApiException("Customer ID not found");
        }
        User user = customer.getUser();
        if (user != null) {
            authRepository.delete(user);
        }
        customerRepository.delete(customer);
    }
}

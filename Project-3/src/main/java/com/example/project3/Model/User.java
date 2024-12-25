package com.example.project3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
//    @NotEmpty(message = "role should not be null")
//    @Pattern(regexp = "ADMIN|CUSTOMER|EMPLOYEE",message = "must be admin or customer or employee")
//    @Column(columnDefinition = "varchar(8) not null check(role='ADMIN' or role='CUSTOMER' or role ='EMPLOYEE')")
    private String role;


    @OneToOne(cascade = CascadeType.ALL , mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Employee employee;
    @OneToOne(cascade = CascadeType.ALL , mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Customer customer;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

package com.example.project3.Service;

import com.example.project3.ApiResponce.ApiException;
import com.example.project3.Model.User;
import com.example.project3.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {


    private final AuthRepository authRepository;


    public List<User> getAllUsers(){
        return authRepository.findAll();
    }

    public User getUser(Integer id){
        User myUser=authRepository.findUserById(id);
        if (myUser==null){
            throw new ApiException("User Not Found!");
        }
        return myUser;
    }

    public void register(User user){
        String hash=new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        authRepository.save(user);

    }


    public void updateUser(Integer auth,User user){
        User olduser = authRepository.findUserById(auth);
        if(olduser==null){
            throw new ApiException("user id not found");
        }
        olduser.setUsername(user.getUsername());
        String hash = new BCryptPasswordEncoder().encode(olduser.getPassword());
        olduser.setPassword(hash);
        authRepository.save(olduser);

    }

    public void deleteUser(Integer auth){
        User user = authRepository.findUserById(auth);
        if(user==null){
            throw new ApiException("id user not found");
        }


        authRepository.delete(user);
    }
}

package com.example.helloworld.Service;

import com.example.helloworld.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.helloworld.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class userService {


    //crud
    @Autowired
    private UserRepository userRepo;

    public User createUser(User user){
        return userRepo.save(user);
    }
    public User getUserById(Long id){
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
    }

}

package com.example.helloworld.Controller;

import com.example.helloworld.Service.userService;
import com.example.helloworld.models.User;
import com.example.helloworld.utils.jwtutil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.helloworld.Repository.UserRepository;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {


    private final userService userser;
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final jwtutil jwtUtil;



    @PostMapping("/register")
    public ResponseEntity<String> resgisternUser(@RequestBody Map<String,String> body){
        //get inputs with that input we create a user  and store in db
        //produce a token using the email address


        String email=body.get("email");
        String password=passwordEncoder.encode(body.get("password"));

        if(userRepo.findByEmail(email).isPresent()){
            return new ResponseEntity<>("Email already exists",HttpStatus.CONFLICT);
            //both can be used
            // return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exist");
        }
        userser.createUser(User.builder().email(email).password(password).build());
        return new ResponseEntity<>("Email registered",HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String,String> body){
        //?--> response entity can be of any type
        String email=body.get("email");
        String password=body.get("password");
        var userOptional = userRepo.findByEmail(email);
        //if there is no user
        if (userOptional.isEmpty()){
            return new ResponseEntity<>("User not found",HttpStatus.UNAUTHORIZED);
        }
        //if there is user
        User user=userOptional.get();
        //after entering we should  not directly store in db we have to encrypt so we use passencoder
        if(!passwordEncoder.matches(password,user.getPassword())){
            return new ResponseEntity<>("Invalid User ",HttpStatus.UNAUTHORIZED);
        }
        //if matched return token using the entered email
        String token=jwtUtil.generateToken(email);
        return ResponseEntity.ok(Map.of("token",token).toString());
        //its 200 response automatically gives && give token as json
    }
}
/*
* getting email and password
* check whether it is in db or not
* if not in db then it is unauthorized
* if password does not match then its unauthorized
* if matched generate token
*/

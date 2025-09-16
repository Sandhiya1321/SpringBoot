package com.example.helloworld;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController


//controller has the control of what the end points should do
public class helloWorldController {


    @GetMapping("/hello")
    //endpoints
    String sayName(){
        return "Sandhiya";
    }
}

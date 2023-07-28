package com.example.task.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.task.Model.userModel;
import com.example.task.service.userService;

@RestController
@RequestMapping(path="/user")
public class userController {

    @Autowired
    userService service;

    

    @PostMapping(path= "/signUp")
    public String addUser(@RequestParam String userName, @RequestParam String password, @RequestParam String confPassword){
        return service.addUser(userName, password, confPassword);

    }

    

    @PostMapping(path= "/login")
    public String login(userModel user){
        return service.authenticateUser(user);
    }

    @GetMapping("/getUsers")
    public List<userModel> getUsers(){
        return service.getUsers();

    }
    
}

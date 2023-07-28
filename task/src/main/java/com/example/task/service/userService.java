package com.example.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;
import com.example.task.Model.userModel;
import com.example.task.Repository.userRepository;

@Service
public class userService {

 @Autowired
 BCryptPasswordEncoder bCrypt;

@Autowired
userRepository repository;

//returns all the users from the repository
public List<userModel> getUsers(){
    return repository.findAll();
}

//checking whether the user is authenticated or not
public String authenticateUser(userModel user) {
    
    //we use optional class because it can even hold null objects
    //getting the user from the input and searching for a user with its username
    Optional<userModel> optionalUser  = repository.findById(user.getUserName());

    //if the user with such username exists 
    if(optionalUser.isPresent()){

        //create another user object for checking with the database
        userModel dbUserModel = optionalUser.get();

        //the password in the database is encrypted
        //the password given by the user is not encrypted
        //we user bCrypt.matches() to check the encrypted password matches with the user given password

        if(bCrypt.matches(user.getPassword(),dbUserModel.getPassword())){
            //if matched, login
            return "Welcome "+user.getUserName();
        }

        //else state that the password is incorrect
        else{
             return "Incorrect Password";
        }
 }
    //if user with such user name is not found
    //throw an exception that the user is not found
    return "User not found";
    
} 


public String addUser(String name, String password, String confPassword){
    Optional<userModel> optionalUser  = repository.findById(name);
    if(optionalUser.isPresent()){
        return "User name already exists";
    }
    if(password.equals(confPassword)){
    userModel user = new userModel(name, confPassword);
    String ePass = bCrypt.encode(confPassword);
    user.setPassword(ePass);
    repository.save(user);
    return user.getUserName()+" is added succesfully";
    }
    return "Passwords dont match";
    

}
    
}

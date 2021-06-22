package com.internship.conference.controllers;

import com.internship.conference.models.Lecture;
import com.internship.conference.models.User;
import com.internship.conference.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/all-users")
    public List<User> getAllUsers(){
        return userService.findAllUsers();
    }

    @GetMapping("/user/{id}")
    public Optional <User> getUser(@PathVariable("id") Integer id){
        return userService.findUser(id);
    }

    @PostMapping("/register")
    public User addUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PutMapping("/editUser")
    public User updateUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUserById(@PathVariable("id") Integer id){
        userService.deleteUser(id);
    }
}

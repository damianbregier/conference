package com.internship.conference.controllers;

import com.internship.conference.models.Lecture;
import com.internship.conference.models.User;
import com.internship.conference.services.LectureService;
import com.internship.conference.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    LectureService lectureService;

    //Find all users
    @GetMapping("/all-users")
    public ResponseEntity<List<User>> getAllUsers(){
        try{
            List<User> usersData = userService.findAllUsers();
            if(usersData.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
                return new ResponseEntity<>(usersData, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Find user by id, return error 404 if doesn't exists
    @GetMapping("/user/{id}")
    public ResponseEntity <User> getUserById(@PathVariable("id") Integer id){
        Optional<User> userData = userService.findUser(id);
        if(userData.isPresent()){
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity <User> addUser(@RequestBody User user){
        try{
            User userData = userService.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editEmail/{id}")
    public ResponseEntity <User> updateUser(@PathVariable("id") Integer id, @RequestBody User user){
        Optional<User> userData = userService.findUser(id);

        if(userData.isPresent()){
            User _user = userData.get();
            _user.setEmail(user.getEmail());
            return new ResponseEntity<>(userService.saveUser(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable("id") Integer id){
        try{
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAllUsers")
    public ResponseEntity <HttpStatus> deleteAllUsers(){
        try{
            userService.deleteAllUsers();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("user/{user_id}/add-reservation/{lecture_id}")
    public ResponseEntity <User> lectureReservation(@PathVariable("user_id") Integer user_id, @PathVariable("lecture_id") Integer lecture_id){
        Optional<User> userData = userService.findUser(user_id);
        Optional <Lecture> lectureData = lectureService.findLecture(lecture_id);

        if(userData.isPresent()){
            User _user = userData.get();
            Lecture _lecture = lectureData.get();
            _user.getReservedLectures().add(_lecture);
            return  new ResponseEntity<>(userService.saveUser(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }




}

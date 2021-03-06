package com.internship.conference.controllers;

import com.internship.conference.models.Lecture;
import com.internship.conference.models.User;
import com.internship.conference.services.LectureService;
import com.internship.conference.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    LectureService lectureService;


    //Find all users
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> usersData = userService.findAllUsers();
            if (usersData.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(usersData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Find user by login, return error 404 if doesn't exists
    @GetMapping("/users/{login}")
    public ResponseEntity<User> getUserByLogin(@PathVariable("login") String login) {
        Optional<User> userData = userService.findUserByLogin(login);
        if (userData.isPresent()) {
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/users")
    public ResponseEntity addUser(@RequestBody User user) {
        String error = "Użytkownik o takim loginie już istnieje!";
        try {
            User userData = userService.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users/{id}/email")
    public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
        Optional<User> userData = userService.findUser(id);

        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setEmail(user.getEmail());
            return new ResponseEntity<>(userService.saveUser(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable("id") Integer id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/users")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        try {
            userService.deleteAllUsers();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("users/{login}/{email}/reservations/{lecture_id}")
    public ResponseEntity<User> lectureReservation(@PathVariable("login") String login, @PathVariable("email") String email, @PathVariable("lecture_id") Integer lecture_id) {
        Optional<User> userData = userService.findUserByLoginAndEmail(login, email);
        Optional<Lecture> lectureData = lectureService.findLecture(lecture_id);

        if (userData.isPresent()) {
            User _user = userData.get();
            Lecture _lecture = lectureData.get();

            //Compare times between lecture and lecture already added to user
            boolean compareStartingTime = _user.getReservedLectures().stream().filter(o -> o.getStartTime().equals(_lecture.getStartTime())).findFirst().isPresent();
            //get number of users added to lecture
            int numberParticipants = (int) _lecture.getParticipants().stream().count();

            //checks times and return conflict in case of the same starting time
            if (compareStartingTime == false && numberParticipants < 5) {
                _user.getReservedLectures().add(_lecture);

                return new ResponseEntity<>(userService.saveUser(_user), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("users/{login}/{email}/reservations-cancel/{lecture_id}")
    public ResponseEntity<User> deleteLectureReservation(@PathVariable("login") String login, @PathVariable("email") String email, @PathVariable("lecture_id") Integer lecture_id) {
        Optional<User> userData = userService.findUserByLoginAndEmail(login, email);
        Optional<Lecture> lectureData = lectureService.findLecture(lecture_id);

        if (userData.isPresent()) {
            User _user = userData.get();
            Lecture _lecture = lectureData.get();
            _user.getReservedLectures().remove(_lecture);
            return new ResponseEntity<>(userService.saveUser(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

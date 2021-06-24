package com.internship.conference.controllers;

import com.internship.conference.models.Lecture;
import com.internship.conference.services.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LectureController {

    @Autowired
    LectureService lectureService;

    //Return list of all lectures - conference's plan
    @GetMapping("/lectures")
    public ResponseEntity <List<Lecture>> getAllLectures(){
        try{
            List <Lecture> lecturesData = lectureService.findAllLectures();
            if(lecturesData.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lecturesData, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Return only one lecture based on id
    @GetMapping("/lectures/{id}")
    public ResponseEntity<Lecture> getLecture(@PathVariable("id") Integer id){
        Optional <Lecture> lectureData = lectureService.findLecture(id);
        if(lectureData.isPresent()){
            return new ResponseEntity<>(lectureData.get(), HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Add single lecture
    @PostMapping("/lectures")
    public ResponseEntity <Lecture> addLecture(@RequestBody Lecture lecture){
        try {
            Lecture lectureData = lectureService.saveLecture(lecture);
            return new ResponseEntity<>(lecture, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/lectures/{id}")
    public ResponseEntity <Lecture> updateLecture(@PathVariable("id") Integer id, @RequestBody Lecture lecture){
        Optional <Lecture> lectureData = lectureService.findLecture(id);

        if(lectureData.isPresent()){
            Lecture _lecture = lectureData.get();
            _lecture.setTitle(lecture.getTitle());
            _lecture.setDescription(lecture.getTitle());
            _lecture.setStartTime(lecture.getStartTime());
            _lecture.setPath(lecture.getPath());
            return new ResponseEntity<>(lectureService.saveLecture(_lecture),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/lectures/{id}")
    public ResponseEntity<HttpStatus> deleteLectureById(@PathVariable("id") Integer id){
        try {
            lectureService.deleteLecture(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
























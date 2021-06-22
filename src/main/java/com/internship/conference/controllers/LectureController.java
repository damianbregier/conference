package com.internship.conference.controllers;

import com.internship.conference.models.Lecture;
import com.internship.conference.services.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LectureController {

    @Autowired
    LectureService lectureService;

    //Return list of all lectures - conference's plan
    @GetMapping("/conference-plan")
    public List<Lecture> getAllLectures(){
        return lectureService.findAllLectures();
    }

    //Return only one lecture based on id
    @GetMapping("/lecture/{id}")
    public Optional<Lecture> getLecture(@PathVariable("id") Integer id){
        return lectureService.findLecture(id);
    }

    //Add single lecture
    @PostMapping("/addLecture")
    public Lecture addLecture(@RequestBody Lecture lecture){
        return lectureService.saveLecture(lecture);
    }

    @PutMapping("/editLecture")
    public Lecture updateLecture(@RequestBody Lecture lecture){
        return lectureService.saveLecture(lecture);
    }

    @DeleteMapping("/deleteLecture/{id}")
    public void deleteLectureById(@PathVariable("id") Integer id){
        lectureService.deleteLecture(id);
    }
}

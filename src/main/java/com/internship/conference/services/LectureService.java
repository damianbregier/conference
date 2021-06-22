package com.internship.conference.services;

import com.internship.conference.models.Lecture;
import com.internship.conference.repositories.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LectureService {

    @Autowired
    LectureRepository lectureRepository;


    public List<Lecture> findAllLectures(){
        return lectureRepository.findAll();
    }

    public Optional<Lecture> findLecture(Integer id){
        return lectureRepository.findById(id);
    }

    public Lecture saveLecture(Lecture lecture){
        return lectureRepository.save(lecture);
    }

    public void deleteLecture(Integer id){
        lectureRepository.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDatabase(){
        saveLecture(new Lecture("testowa1", "Opis testowy 1", "10.00", 1));
        saveLecture(new Lecture("testowa2", "Opis testowy 2", "11.45", 2));
        saveLecture(new Lecture("testowa3", "Opis testowy 3", "13.30", 3));

    }



}

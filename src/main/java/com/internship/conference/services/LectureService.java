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
    public void fillLecturesTable(){
        saveLecture(new Lecture("Prelekcja 1", "Opis testowy 1", "10.00", 1));
        saveLecture(new Lecture("Prelekcja 2", "Opis testowy 2", "10.00", 2));
        saveLecture(new Lecture("Prelekcja 3", "Opis testowy 3", "10.00", 3));

        saveLecture(new Lecture("Prelekcja 4", "Opis testowy 4", "12.00", 1));
        saveLecture(new Lecture("Prelekcja 5", "Opis testowy 5", "12.00", 2));
        saveLecture(new Lecture("Prelekcja 6", "Opis testowy 6", "12.00", 3));

        saveLecture(new Lecture("Prelekcja 7", "Opis testowy 7", "14.00", 1));
        saveLecture(new Lecture("Prelekcja 8", "Opis testowy 8", "14.00", 2));
        saveLecture(new Lecture("Prelekcja 9", "Opis testowy 9", "14.00", 3));

    }



}

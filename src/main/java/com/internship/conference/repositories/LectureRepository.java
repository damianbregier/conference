package com.internship.conference.repositories;


import com.internship.conference.models.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRepository extends JpaRepository <Lecture, Integer> {

}

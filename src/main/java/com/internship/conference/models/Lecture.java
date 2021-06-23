package com.internship.conference.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "lectures")
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "startTime")
    private String startTime;

    @Column(name = "path")
    private Integer path;

    @ManyToMany(mappedBy = "reserved" )
    Set<User> reservation;



    public Lecture() {
    }

    public Lecture(String title, String description, String startTime, Integer path) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.path = path;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Integer getPath() {
        return path;
    }

    public void setPath(Integer path) {
        this.path = path;
    }
}

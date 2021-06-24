package com.internship.conference.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "login")
    private String login;

    @Column(name = "email")
    private String email;


    @ManyToMany
    @JoinTable(name = "reserved_lectures", joinColumns = @JoinColumn(
            name = "user_id"), inverseJoinColumns = @JoinColumn(name = "lecture_id"))
    Set<Lecture> reserved;


    public User() {
    }

    public User(String name, String surname, String login, String email) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Lecture> getReserved() {
        return reserved;
    }

    public void setReserved(Set<Lecture> reserved) {
        this.reserved = reserved;
    }
}



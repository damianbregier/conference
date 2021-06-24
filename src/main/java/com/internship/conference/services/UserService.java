package com.internship.conference.services;

import com.internship.conference.models.Lecture;
import com.internship.conference.models.User;
import com.internship.conference.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> findUser(Integer id){
        return userRepository.findById(id);
    }

    public  Optional <User> findUserByLogin(String login){
        return userRepository.findUserByLogin(login);
    }

    public Optional <User> findUserByLoginAndEmail(String login, String email){
        return userRepository.findUserByLoginAndEmail(login, email);
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }

    public void deleteAllUsers(){
        userRepository.deleteAll();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillUsersTable(){
        saveUser(new User("Jan", "Kowalski", "jkow", "jkow@gmail.com" ));
        saveUser(new User("Mikołaj", "Wiśniewski", "mkwisniewski", "wisniewski@gmail.com"));
        saveUser(new User("Maciej", "Kość", "mkosc", "kosc@gmail.com"));
        saveUser(new User("Tomasz", "Jabłoński", "tjablon", "jablonski@gmail.com"));
        saveUser(new User("Piotr", "Nowak", "pnowak", "nowak@gmail.com"));
        saveUser(new User("Mateusz", "Tomczak", "mtomczak", "mtomczak@gmail.com"));
    }

}

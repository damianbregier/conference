package com.internship.conference.repositories;

import com.internship.conference.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User,Integer> {
    public Optional<User> findUserByLogin(String login);
    public Optional<User> findUserByLoginAndEmail(String login, String email);

}

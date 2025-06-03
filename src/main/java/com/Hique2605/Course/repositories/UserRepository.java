package com.Hique2605.Course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Hique2605.Course.entities.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

     Optional<User>  findByEmail(String email);

}


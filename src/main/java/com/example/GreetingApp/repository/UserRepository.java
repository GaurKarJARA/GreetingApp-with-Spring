package com.example.GreetingApp.repository;
import com.example.GreetingApp.models.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
    @Repository
    public interface UserRepository extends JpaRepository<AuthUser, Long> {


    }


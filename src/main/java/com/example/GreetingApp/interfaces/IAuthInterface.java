package com.example.GreetingApp.interfaces;

import com.example.GreetingApp.dto.AuthUserDTO;
import com.example.GreetingApp.dto.LoginDTO;
import org.springframework.stereotype.Service;

    @Service
    public interface IAuthInterface {

        public String register(AuthUserDTO user);

        public String login(LoginDTO user);

    }


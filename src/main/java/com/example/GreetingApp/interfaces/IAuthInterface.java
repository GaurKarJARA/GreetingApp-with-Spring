package com.example.GreetingApp.interfaces;

import com.example.GreetingApp.dto.AuthUserDTO;
import com.example.GreetingApp.dto.LoginDTO;
import com.example.GreetingApp.dto.PassDTO;
import org.springframework.stereotype.Service;

    @Service
    public interface IAuthInterface {

        public String register(AuthUserDTO user);

        public String login(LoginDTO user);

        //UC-Forgot password
        public AuthUserDTO forgotpassword(PassDTO pass,String email);

        //UC-Reset password
        public String resetPassword(String email, String currentPass, String newPass);
    }


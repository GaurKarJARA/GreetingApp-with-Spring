package com.example.GreetingApp.controllers;
import com.example.GreetingApp.dto.AuthUserDTO;
import com.example.GreetingApp.service.*;
import com.example.GreetingApp.dto.LoginDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    EmailService emailService;
    AuthenticationService authenticationService;
    JwtTokenService jwtTokenService;

    public UserController(EmailService emailService, AuthenticationService authenticationService,JwtTokenService jwtTokenService ) {
        this.emailService = emailService;
        this.authenticationService = authenticationService;
        this.jwtTokenService = jwtTokenService;
    }

    //UC-UserRegistration --> For Registration of a user
    @PostMapping(path = "/register")
    public String register(@RequestBody AuthUserDTO user){
        return authenticationService.register(user);
    }

    //UC-login --> For User Login
    @PostMapping(path ="/login")
    public String login(@RequestBody LoginDTO user){
        return authenticationService.login(user);
    }

}
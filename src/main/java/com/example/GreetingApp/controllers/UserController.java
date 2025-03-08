package com.example.GreetingApp.controllers;
import com.example.GreetingApp.interfaces.IAuthInterface;
import com.example.GreetingApp.service.*;
import com.example.GreetingApp.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    EmailService emailService;

    //Replacing constructor injection with interface -(UC-Adding-Interfaces_Autowired)
    IAuthInterface iAuthInterface;

    //UC-UserRegistration --> For Registration of a user
    @PostMapping(path = "/register")
    public String register(@RequestBody AuthUserDTO user){
        return iAuthInterface.register(user);
    }

    //UC-login --> For User Login
    @PostMapping(path ="/login")
    public String login(@RequestBody LoginDTO user){
        return iAuthInterface.login(user);
    }
    //UC-SendingMail --> For sending mail to another person
    @PostMapping(path = "/sendMail")
    public String sendMail(@RequestBody MailDTO message){
        emailService.sendEmail(message.getTo(), message.getSubject(), message.getBody());
        return "Mail sent";
    }

        //UC-forgot password
    @PutMapping("/forgotpassword/{email}")
    public AuthUserDTO forgotpassword(@RequestBody PassDTO pass,@PathVariable String email) {
        return iAuthInterface.forgotpassword(pass,email);
    }
        //UC-reset password
    @PutMapping("/resetPassword/{email}")
    public String resetPassword(@PathVariable String email ,@RequestParam String currentPass, @RequestParam String newPass){
        return iAuthInterface.resetPassword(email, currentPass, newPass);
    }

    }
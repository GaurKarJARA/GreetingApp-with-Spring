package com.example.GreetingApp.service;
import com.example.GreetingApp.dto.LoginDTO;
import com.example.GreetingApp.dto.AuthUserDTO;
import com.example.GreetingApp.dto.PassDTO;
import com.example.GreetingApp.interfaces.IAuthInterface;
import com.example.GreetingApp.models.AuthUser;
import com.example.GreetingApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

    @Service
    public class AuthenticationService implements IAuthInterface {
        @Autowired
        UserRepository userRepository;
        @Autowired
        EmailService emailService;
        @Autowired
        JwtTokenService jwtTokenService;


        public String register(AuthUserDTO user){

            List<AuthUser> l1 = userRepository.findAll().stream().filter(authuser -> user.getEmail().equals(authuser.getEmail())).collect(Collectors.toList());

            if(l1.size()>0){
                return "User already registered";
            }

            //creating hashed password using bcrypt
            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
            String hashPass = bcrypt.encode(user.getPassword());

            //creating new user
            AuthUser newUser = new AuthUser(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), hashPass);

            //setting the new hashed password
            newUser.setHashPass(hashPass);

            //saving the user in the database
            userRepository.save(newUser);

            //sending the confirmation mail to the user
            emailService.sendEmail(user.getEmail(), "Regitration Status", user.getFirstName()+" you are registered!");

            return "user registered";
        }
        public String login(LoginDTO user){

            List<AuthUser> l1 = userRepository.findAll().stream().filter(authuser -> authuser.getEmail().equals(user.getEmail())).collect(Collectors.toList());
            if(l1.size() == 0)
                return "User not registered";

            AuthUser foundUser = l1.get(0);

            //matching the stored hashed password with the password provided by user
            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

            if(!bcrypt.matches(user.getPassword(), foundUser.getHashPass()))
                return "Invalid password";

            //creating Jwt Token
            String token = jwtTokenService.createToken(foundUser.getId());

            //setting token for user login
            foundUser.setToken(token);

            //saving the current status of user in database
            userRepository.save(foundUser);

            return "user logged in"+"\ntoken : "+token;
        }

        //UC-forgot password
        public AuthUserDTO forgotpassword(PassDTO pass,String email){

            AuthUser foundUser = userRepository.findByEmail(email);

            if(foundUser == null)
                throw new RuntimeException("user not registered!");

            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String hashpass = bCryptPasswordEncoder.encode(pass.getNewPassword());

            foundUser.setPassword(pass.getNewPassword());
            foundUser.setHashPass(hashpass);

            userRepository.save(foundUser);


            emailService.sendEmail(email,"Password Reset status","Password has been reset");
            AuthUserDTO resDto = new AuthUserDTO(foundUser.getFirstName(), foundUser.getLastName(), foundUser.getEmail(), foundUser.getPassword(), foundUser.getId() );

            return resDto;
        }

    }


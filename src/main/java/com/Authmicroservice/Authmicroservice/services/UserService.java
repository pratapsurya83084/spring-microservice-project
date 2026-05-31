package com.Authmicroservice.Authmicroservice.services;


import com.Authmicroservice.Authmicroservice.models.JwtTokenResponseDto;
import com.Authmicroservice.Authmicroservice.models.User;
import com.Authmicroservice.Authmicroservice.models.UserDto;
import com.Authmicroservice.Authmicroservice.repository.UserRepository;
import com.Authmicroservice.Authmicroservice.utils.JwtUtil;
import io.jsonwebtoken.Jwt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    Logger log = LoggerFactory.getLogger(UserService.class);


    //create bean  Using constructor
       private final  JwtUtil jwtUtil;

    private final UserRepository userRepository;

       private final PasswordEncoder passwordEncoder;

     UserService(UserRepository userRepository  , PasswordEncoder passwordEncoder,JwtUtil jwtUtil){
         this.userRepository  = userRepository;
         this.passwordEncoder= passwordEncoder;
         this.jwtUtil = jwtUtil;
     }


     //create   service for register user
    public UserDto registerUser(User user){
         // register user steps 1 . check user already exists with Given email- if yes then show message else register

       List<User> userList =  userRepository.findAll();
       for(User useris : userList){
           if (useris.getEmail().equals(user.getEmail())){ // cannot able to use == oprator because it is object and .equals() is object method that why i used here.
               throw new RuntimeException("User Already Exists ,Please login ");
                //log.info("User Already Exists ,Please login");
           }
       }

        // step 2. hash or encrypt the password and then store in db


       String hashPassword =   passwordEncoder.encode(user.getPassword());
//       log.info("password is Hashe or Encrypt...." + hashPassword);
       user.setPassword(hashPassword);

       User savedUser =  userRepository.save(user);

       log.info("user registeer successfull");

        return new UserDto(savedUser.getId() ,
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getRoles()
        );
    }


     /// getToken or generate token
     public JwtTokenResponseDto generateJwtToken(String Username){
       String token =  jwtUtil.generateToken(Username);

         JwtTokenResponseDto jwttoken = new JwtTokenResponseDto();

         jwttoken.setToken(token);
         jwttoken.setType("Bearer");
         jwttoken.setValidUntil(jwtUtil.extractExpiration(token).toString());

         return jwttoken;
     }

}

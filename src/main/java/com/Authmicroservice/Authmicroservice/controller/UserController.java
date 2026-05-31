package com.Authmicroservice.Authmicroservice.controller;

import com.Authmicroservice.Authmicroservice.exception.BadRequestException;
import com.Authmicroservice.Authmicroservice.models.JwtTokenResponseDto;
import com.Authmicroservice.Authmicroservice.models.LoginRequestDto;
import com.Authmicroservice.Authmicroservice.models.User;
import com.Authmicroservice.Authmicroservice.models.UserDto;
import com.Authmicroservice.Authmicroservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;


    public UserController(UserService userService , AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register-user")
    public ResponseEntity<UserDto> registerUser(@RequestBody User user){
        UserDto userdto = userService.registerUser(user);
      return  new ResponseEntity<>(userdto, HttpStatus.CREATED);
    }

    @PostMapping("/generate-token")
    public JwtTokenResponseDto generateToken(@RequestBody LoginRequestDto loginRequestDto){
    try{
        Authentication  authentication  =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),loginRequestDto.getPassword()));
        if (authentication.isAuthenticated()){
            return userService.generateJwtToken(loginRequestDto.getUsername());
        }else{
            throw  new BadRequestException("Invalid Credentials...");
        }
    } catch (Exception e){
        e.printStackTrace();
        throw new BadRequestException("Invalid creadentials :"+ e.getMessage());

    }

    }


}

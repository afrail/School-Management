package com.shopnobazz.schoolManagement.controller;


import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shopnobazz.schoolManagement.dto.LoginRequest;
import com.shopnobazz.schoolManagement.dto.UserDto;
import com.shopnobazz.schoolManagement.service.SigninAndSignup;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/public")
@AllArgsConstructor
public class SingAndSignupController {
	
	@Autowired
    SigninAndSignup signinAndSignup;
@PostMapping("/signin")
public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest){

    return ResponseEntity.ok(signinAndSignup.login(loginRequest));

}
@PostMapping("/signup")
public ResponseEntity<?> registerUser(@RequestBody UserDto userDto)
{
    return ResponseEntity.ok(signinAndSignup.signup(userDto));
}
}

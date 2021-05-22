package com.shopnobazz.schoolManagement.service;

import com.shopnobazz.schoolManagement.domain.Role;
import com.shopnobazz.schoolManagement.domain.User;
import com.shopnobazz.schoolManagement.dto.LoginRequest;
import com.shopnobazz.schoolManagement.dto.RoleDto;
import com.shopnobazz.schoolManagement.dto.UserDto;
import com.shopnobazz.schoolManagement.jwt.JwtTokenProvider;
import com.shopnobazz.schoolManagement.repository.RoleRepository;
import com.shopnobazz.schoolManagement.repository.UserRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SigninAndSignup {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtProvider;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
     RoleRepository roleRepository;
public ResponseEntity<String> signup(UserDto userDto){
	System.out.println(userDto);

    if(userRepository.findByUserName(userDto.getUserName()) !=null)
    {

        return new ResponseEntity<String>("this user name already register",HttpStatus.BAD_REQUEST);
    }else
    {
        User user = new User();
       List<Role> roles = new ArrayList<>();
       Role role = new Role();
//       userDto.getRoles().stream().map(mapper -> {});
        
        for(RoleDto dto: userDto.getRoles())
        {
        	System.out.println(dto);
        	System.out.println(role);
          BeanUtils.copyProperties(dto, role);
          System.out.println(role);
          roles.add(role);
        }
        
//        .forEach(rol -> {
//        	BeanUtils.copyProperties(rol, role);
//            roles.add(role);
//        });

        roles.stream().forEach(role2 ->{ 
        	System.out.println(role2);
        	roleRepository.save(role2);

        });
         user.setFirstName(userDto.getFirstName());
         user.setLastName(userDto.getLastName());
         user.setUserName(userDto.getUserName());
         user.setEmail(userDto.getEmail());
         user.setPassword(encoder.encode(userDto.getPassword()));
         user.setPhone(userDto.getPhone());
         user.setRoles(roles);
         userRepository.save(user);
        }


    return new ResponseEntity<String>(userDto.getUserName(),HttpStatus.OK);
    }


  public String login(LoginRequest loginRequest){
      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                      loginRequest.getUsername(),
                      loginRequest.getPassword()
              )
      );

      SecurityContextHolder.getContext().setAuthentication(authentication);

      String jwt = jwtProvider.generateJwtToken(authentication);
      System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
    return jwt;
  }



}


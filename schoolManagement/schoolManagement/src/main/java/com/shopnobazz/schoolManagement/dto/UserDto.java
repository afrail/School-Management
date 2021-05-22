package com.shopnobazz.schoolManagement.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class UserDto {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private List<RoleDto> roles;
}

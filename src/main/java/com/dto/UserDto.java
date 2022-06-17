package com.dto;

import lombok.Data;

@Data
public class UserDto {
    String name;
    String email;
    String password;
    String role;
    String location;
    Long id;
}

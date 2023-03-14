package com.example.jwtdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    private int id;
    private String userName;
    private String password;
    private String phoneNumber;
    private String dob;
    private String login_source;
    private String email;
    private Integer roleId;
}

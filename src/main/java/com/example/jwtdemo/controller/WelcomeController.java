package com.example.jwtdemo.controller;

import com.example.jwtdemo.entity.AuthRequest;
import com.example.jwtdemo.entity.UserNew;
import com.example.jwtdemo.repo.UserNewRepository;
import com.example.jwtdemo.service.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserNewRepository userNewRepository;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to Bellurbis Technology !!";
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
//        return jwtUtil.generateToken(authRequest.getUserName(), authRequest.getPhoneNumber(), authRequest.getEmail(), authRequest.getRoleId(), authRequest.getDob(), authRequest.getLogin_source());

        UserNew userNew = userNewRepository.findByUserName(authRequest.getUserName());
        return jwtUtil.generateToken(userNew, userNew.getRoleId(), authRequest.getLogin_source());
    }
}

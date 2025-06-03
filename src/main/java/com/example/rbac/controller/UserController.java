package com.example.rbac.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/dashboard")
    public String userDashboard() {
        return "user dashboard";
    }

}

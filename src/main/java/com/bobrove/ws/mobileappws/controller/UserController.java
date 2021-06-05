package com.bobrove.ws.mobileappws.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @GetMapping
    public String getUser() {
        return "getUser() called";
    }

    @PostMapping
    public String createUser() {
        return "getUser() called";
    }

    @PutMapping
    public String updateUser() {
        return "updateUser() called";
    }

    @DeleteMapping
    public String deleteUser() {
        return "deleteUser() called";
    }
}

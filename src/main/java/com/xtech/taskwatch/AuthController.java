package com.xtech.taskwatch;

import com.xtech.taskwatch.model.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/register/add")
    public String registerUser() {
        return "redirect:/logout";
    }
}

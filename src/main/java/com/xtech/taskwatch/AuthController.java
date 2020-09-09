package com.xtech.taskwatch;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import com.xtech.taskwatch.model.User;
import com.xtech.taskwatch.model.UserRepository;
import com.xtech.taskwatch.model.UserRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/register")
    public String register() {
        return "register";
    }/**/

    @PostMapping("/register")
    public String registerUser(User user, Map<String, Object> model) {
        Optional<User> fromDb = userRepo.findByUsername(user.getUsername());
        if (fromDb.isPresent())
        {
            model.put("message", "User already exists!");
            return "register";
        }

        user.setUserRoles(Collections.singleton(UserRole.USER));

        userRepo.save(user);

        return "redirect:/list";
    }
}

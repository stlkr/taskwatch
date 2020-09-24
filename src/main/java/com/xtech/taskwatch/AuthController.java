package com.xtech.taskwatch;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import com.xtech.taskwatch.model.User;
import com.xtech.taskwatch.model.UserRepository;
import com.xtech.taskwatch.model.UserRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/register")
    public String register(Map<String, Object> model) {
        model.put("action", "register");
        return "register";
    }/**/

    @PostMapping("/register")
    public String registerUser(User user, Map<String, Object> model) {
        Optional<User> fromDb = userRepo.findByUsername(user.getUsername());
        if (fromDb.isPresent())
        {
            model.put("message", "Пользователь уже существует!");
            return "register";
        }

        user.setUserRoles(Collections.singleton(UserRole.USER));
        userRepo.save(user);

        return "redirect:/list";
    }

    @GetMapping("/admin")
    public String adminList(Map<String, Object> model, @AuthenticationPrincipal User current_user) {
        Iterable<User> users;

        users = userRepo.findAll();
        
        model.put("admin", "1");
        model.put("users", users);
        model.put("session_user", current_user.getUsername());

        return "admin";
    }

    @GetMapping("/admin/edit")
    public String adminEdit(Map<String, Object> model, @RequestParam Long id, @AuthenticationPrincipal User current_user) {
        Optional<User> dbUser = userRepo.findById(id);
        if(!dbUser.isPresent())
        {
            model.put("message", "Пользователь не найден!");
            return "register";
        }

        User user = dbUser.get();
        model.put("action", "admin/edit");
        model.put("username", user.getUsername());
        model.put("password", user.getPassword());
        model.put("email", user.getEmail());

        model.put("session_user", current_user.getUsername());

        return "register";
    }

    @PostMapping("/admin/edit")
    public String adminEditSubmit(User user, Map<String, Object> model) {
        Optional<User> fromDb = userRepo.findByUsername(user.getUsername());
        if (fromDb.isPresent())
        {
            User u = fromDb.get();
            if(!u.getUsername().equals("admin"))
                u.setUsername(user.getUsername());
            u.setEmail(user.getEmail());
            u.setPassword(user.getPassword());
            userRepo.save(u);
            return "redirect:/admin";
        }

        model.put("message", "Пользователь не найден!");
        return "register";
    }

    @GetMapping("/admin/delete")
    public String adminDelete(@RequestParam Long id) {
        Optional<User> dbUser = userRepo.findById(id);
        User u = dbUser.get();
        if(dbUser.isPresent() && !u.getUsername().equals("admin"))
            userRepo.delete(u);
        return "redirect:/admin";
    }
}

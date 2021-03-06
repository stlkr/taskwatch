package com.xtech.taskwatch;

import java.util.Optional;

import com.xtech.taskwatch.model.User;
import com.xtech.taskwatch.model.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repo.findByUsername(username);
        if(!user.isPresent())
            throw new UsernameNotFoundException("User not found in database");
        return user.get();
    }
    
}

package com.localshopper.backend.Service;

import com.localshopper.backend.Model.User;
import com.localshopper.backend.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String email, String password) {
        return userRepository
                .findByEmailAndPassword(email, password)
                .orElse(null);
    }
}

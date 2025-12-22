package com.localshopper.backend.Controller;

import com.localshopper.backend.Model.User;
import com.localshopper.backend.Repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ✅ Create user (OWNER or CUSTOMER)
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // (Optional) Get all users — useful for debugging
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

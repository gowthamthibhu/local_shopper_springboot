package com.localshopper.backend.Controller;

import com.localshopper.backend.Model.User;
import com.localshopper.backend.Repository.UserRepository;
import com.localshopper.backend.dto.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        String email = request.getEmail().trim();
        String password = request.getPassword().trim();

        System.out.println("LOGIN ATTEMPT:");
        System.out.println("Email: [" + email + "]");
        System.out.println("Password: [" + password + "]");

        User user = userRepository
                .findByEmailAndPassword(email, password)
                .orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid credentials");
        }

        return ResponseEntity.ok(
                Map.of(
                        "userId", user.getId(),
                        "name", user.getName(),
                        "role", user.getRole()
                )
        );
    }

}


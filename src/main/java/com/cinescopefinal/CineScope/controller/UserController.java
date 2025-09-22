package com.cinescopefinal.CineScope.controller;

import com.cinescopefinal.CineScope.dto.StatusUpdateRequest;
import com.cinescopefinal.CineScope.entities.Users;
import com.cinescopefinal.CineScope.repository.UserRepository;
import com.cinescopefinal.CineScope.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<Users>> getAllUsers() {
        try {
            List<Users> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PatchMapping("/updateStatus/{id}")
    public ResponseEntity<Users> updateStatus(@PathVariable Long id, @RequestBody StatusUpdateRequest req) {
        Optional<Users> user = userRepository.findById(id);
        if (user.isPresent()) {
            Users newUser = user.get();
            newUser.setStatus(req.getStatus());
            userRepository.save(newUser);
            return ResponseEntity.ok(newUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

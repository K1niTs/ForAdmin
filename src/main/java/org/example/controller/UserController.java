package org.example.controller;


import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user.getName());
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        Optional<User> user = userService.getUserById(id);
        return user.orElse(null);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        Optional<User> updatedUser = userService.updateUser(id, user.getName());
        return updatedUser.orElse(null);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        return userService.deleteUser(id) ? "User deleted" : "User not found";
    }
}

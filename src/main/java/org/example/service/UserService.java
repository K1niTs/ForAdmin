package org.example.service;


import org.example.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private List<User> users = new ArrayList<>() {{
        add(new User(1, "Alice"));
        add(new User(2, "Bob"));
        add(new User(3, "Charlie"));
    }};

    private int currentId = 4;

    public User createUser(String name) {
        User user = new User(currentId++, name);
        users.add(user);
        return user;
    }

    public List<User> getAllUsers() {
        return users;
    }

    public Optional<User> getUserById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }

    public Optional<User> updateUser(int id, String name) {
        for (User user : users) {
            if (user.getId() == id) {
                user.setName(name);
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public boolean deleteUser(int id) {
        return users.removeIf(user -> user.getId() == id);
    }
}

package com.mindera.school.multiplayer.http.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.net.URI;
import java.util.List;

import com.mindera.school.multiplayer.http.models.User;
import com.mindera.school.multiplayer.services.UserService;

@RestController
@RequestMapping("/users")
public class ContUser {

    private final UserService userService;

    @Autowired
    public ContUser(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        var newUser = userService.createUser(user);

        return ResponseEntity.created(getUserURI(newUser.getId())).build();
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId") String userId) {
        return userService.getUser(userId);
    }

    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
    }

    @PutMapping("/{userId}")
    public User replaceUser(
            @PathVariable("userId") String userId,
            @RequestBody User user
    ) {
        return userService.replaceUser(userId, user);
    }

    private URI getUserURI(String id) {
        return URI.create(String.format("/users/%s", id));
    }
}

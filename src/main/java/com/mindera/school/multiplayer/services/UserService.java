package com.mindera.school.multiplayer.services;

import com.mindera.school.multiplayer.http.models.User;

import java.util.List;

public interface UserService {

    User createUser(User newUser);

    User getUser(String userId);

    List<User> getAllUsers();

    void deleteUser(String userId);

    User replaceUser(String userId, User user);
}

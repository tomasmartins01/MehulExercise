package com.mindera.school.multiplayer.exceptions;

public class UserDoesntExist extends RuntimeException {
    public UserDoesntExist(String id) {
        super(String.format("User %s not found", id));
    }
}
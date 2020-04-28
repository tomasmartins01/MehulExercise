package com.mindera.school.multiplayer.exceptions;

public class LobbyDoesntExist extends RuntimeException {
    public LobbyDoesntExist(String id) {
        super(String.format("Lobby %s not found", id));
    }
}
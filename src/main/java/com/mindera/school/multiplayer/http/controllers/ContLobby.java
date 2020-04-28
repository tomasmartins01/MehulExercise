package com.mindera.school.multiplayer.http.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.net.URI;
import java.util.List;

import com.mindera.school.multiplayer.http.models.Lobby;
import com.mindera.school.multiplayer.http.models.User;
import com.mindera.school.multiplayer.services.LobbyService;

@RestController
@RequestMapping("/lobbies")
public class ContLobby {

    private LobbyService lobbyService;

    @Autowired
    public ContLobby(LobbyService lobbyService) {
        this.lobbyService = lobbyService;
    }

    @PostMapping("")
    public ResponseEntity<Void> createLobby() {
        var newLobby = lobbyService.createLobby();
        return ResponseEntity.created(getLobbyURI(newLobby.getId())).build();
    }

    @GetMapping("")
    public List<Lobby> getLobbies() {
        return lobbyService.getLobbies();
    }

    @DeleteMapping("/{lobbyId}")
    public void deleteLobby(@PathVariable("lobbyId") String lobbyId) {
        lobbyService.deleteLobby(lobbyId);
    }

    @PostMapping("/{lobbyId}/users")
    public void addUserOnLobby(
            @PathVariable("lobbyId") String lobbyId,
            @RequestBody User user
    ) {
        lobbyService.addUserOnLobby(user, lobbyId);
    }

    @DeleteMapping("/{lobbyId}/users/{userId}")
    public void removeUserFromLobby(@PathVariable("lobbyId") String lobbyId, @PathVariable("userId") String userId) {
        lobbyService.removeUserFromLobby(userId, lobbyId);
    }

    private URI getLobbyURI(String id) {
        return URI.create(String.format("/lobbies/%s", id));
    }
}
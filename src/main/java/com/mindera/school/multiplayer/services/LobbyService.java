package com.mindera.school.multiplayer.services;

import com.mindera.school.multiplayer.http.models.Lobby;
import com.mindera.school.multiplayer.http.models.User;

import java.util.List;

public interface LobbyService {
    Lobby createLobby();

    List<Lobby> getLobbies();

    void deleteLobby(String lobbyId);

    void addUserOnLobby(User user, String lobbyId);

    void removeUserFromLobby(String userId, String lobbyId);

}

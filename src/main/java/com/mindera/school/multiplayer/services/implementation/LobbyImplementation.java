package com.mindera.school.multiplayer.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.mindera.school.multiplayer.data.entities.EntLobby;
import com.mindera.school.multiplayer.data.entities.EntUser;
import com.mindera.school.multiplayer.data.repositories.RepLobby;
import com.mindera.school.multiplayer.data.repositories.RepUser;
import com.mindera.school.multiplayer.exceptions.LobbyDoesntExist;
import com.mindera.school.multiplayer.http.models.Lobby;
import com.mindera.school.multiplayer.http.models.User;
import com.mindera.school.multiplayer.services.LobbyService;

@Service
public class LobbyImplementation implements LobbyService {

    private RepLobby lobbyRepository;

    private RepUser userRepository;

    @Autowired
    public LobbyImplementation(RepLobby lobbyRepository, RepUser userRepository) {
        this.lobbyRepository = lobbyRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Lobby createLobby() {
        var lobby = new EntLobby();
        lobby.setId(UUID.randomUUID().toString());

        return mapFromLobbyEntity(this.lobbyRepository.save(lobby));
    }

    @Override
    public List<Lobby> getLobbies() {
        var allLobbies = new ArrayList<Lobby>();
        lobbyRepository.findAll()
                .forEach(lobbyEntity -> allLobbies.add(mapFromLobbyEntity(lobbyEntity)));
        return allLobbies;
    }

    @Override
    public void deleteLobby(String lobbyId) {
        try {
            lobbyRepository.deleteById(lobbyId);
        } catch (EmptyResultDataAccessException e) {
            throw new LobbyDoesntExist(lobbyId);
        }
    }

    @Override
    public void addUserOnLobby(User user, String lobbyId) {
        var lobbyEntity = new EntLobby();
        lobbyEntity.setId(lobbyId);

        var userEntity = mapFromUser(user);
        userEntity.setLobby(lobbyEntity);

        userRepository.save(userEntity);
    }

    @Override
    public void removeUserFromLobby(String userId, String lobbyId) {
        var userEntity = this.userRepository.findById(userId);
        userEntity.ifPresent(entity -> {
            entity.setLobby(null);
            userRepository.save(entity);
        });
    }

    private Lobby mapFromLobbyEntity(EntLobby lobbyEntity) {
        return new Lobby(lobbyEntity.getId());
    }

    private EntLobby mapFromLobby(Lobby lobby) {
        var lobbyEntity = new EntLobby();
        lobbyEntity.setId(lobby.getId());
        return lobbyEntity;
    }

    private EntUser mapFromUser(User user) {
        var userEntity = new EntUser();
        userEntity.setId(user.getId());
        userEntity.setName(user.getName());
        return userEntity;
    }
}

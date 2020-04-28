package com.mindera.school.multiplayer.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.mindera.school.multiplayer.data.entities.EntUser;
import com.mindera.school.multiplayer.data.repositories.RepUser;
import com.mindera.school.multiplayer.exceptions.UserDoesntExist;
import com.mindera.school.multiplayer.http.models.User;
import com.mindera.school.multiplayer.services.UserService;

@Service
public class UserImplementation implements UserService {
    private RepUser userRepository;

    @Autowired
    public UserImplementation(RepUser userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User newUser) {
        //Create new Entity
        var entity = new EntUser();
        entity.setId(UUID.randomUUID().toString());
        entity.setName(newUser.getName());

        //Save
        return mapFromUserEntity(this.userRepository.save(entity));
    }

    @Override
    public User getUser(String userId) {
        return this.userRepository
                .findById(userId)
                .map(this::mapFromUserEntity).orElseThrow(() -> new UserDoesntExist(userId));
    }

    @Override
    public List<User> getAllUsers() {
        var allUsers = new ArrayList<User>();
        userRepository.findAll()
                .forEach(user -> allUsers.add(mapFromUserEntity(user)));
        return allUsers;
    }

    @Override
    public void deleteUser(String userId) {
        try {
            userRepository.deleteById(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new UserDoesntExist(userId);
        }
    }

    @Override
    public User replaceUser(String userId, User user) {
        if (userRepository.findById(userId).isEmpty())
            throw new UserDoesntExist(userId);

        user.setId(userId);

        return mapFromUserEntity(
                userRepository.save(mapFromUser(user))
        );
    }

    private User mapFromUserEntity(EntUser userEntity) {
        return new User(userEntity.getId(), userEntity.getName());
    }

    private EntUser mapFromUser(User user) {
        var userEntity = new EntUser();
        userEntity.setId(user.getId());
        userEntity.setName(user.getName());
        return userEntity;
    }
}

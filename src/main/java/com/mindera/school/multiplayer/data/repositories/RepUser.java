package com.mindera.school.multiplayer.data.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import com.mindera.school.multiplayer.data.entities.EntUser;

@Repository
public interface RepUser extends CrudRepository<EntUser, String> {
}

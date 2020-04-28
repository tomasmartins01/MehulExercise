package com.mindera.school.multiplayer.data.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import com.mindera.school.multiplayer.data.entities.EntLobby;

@Repository
public interface RepLobby extends CrudRepository<EntLobby, String> {
}

package com.mindera.school.multiplayer.data.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;

@Entity
public class EntUser {
    @Id
    private String id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private EntLobby lobby;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public EntLobby getLobby() {
        return lobby;
    }
    public void setLobby(EntLobby lobby) {
        this.lobby = lobby;
    }
}

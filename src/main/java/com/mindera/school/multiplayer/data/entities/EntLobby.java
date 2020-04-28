package com.mindera.school.multiplayer.data.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import java.util.List;

@Entity
public class EntLobby {
    @Id
    private String id;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "lobby")
    private List<EntUser> users;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
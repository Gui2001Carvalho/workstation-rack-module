package com.ctw.workstation.team.DTOs;

import com.ctw.workstation.team.entity.Team;

import java.util.UUID;

public class TeamOutputDTO {
    public UUID id;
    public String name;
    public String product;
    public String defaultLocation;

    public TeamOutputDTO() {}

    public TeamOutputDTO(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.product = team.getProduct();
        this.defaultLocation = team.getDefaultLocation();
    }
}

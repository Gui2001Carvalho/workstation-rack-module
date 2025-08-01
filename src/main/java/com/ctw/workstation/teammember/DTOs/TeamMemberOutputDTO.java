package com.ctw.workstation.teammember.DTOs;

import com.ctw.workstation.teammember.entity.TeamMember;

import java.time.LocalDateTime;
import java.util.UUID;

public class TeamMemberOutputDTO {

    public UUID id;
    public String name;
    public String ctw_id;
    public UUID teamID;

    public TeamMemberOutputDTO() {}

    public TeamMemberOutputDTO(TeamMember entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.ctw_id = entity.getCtw_id();
        this.teamID = entity.getTeamID();
    }
}


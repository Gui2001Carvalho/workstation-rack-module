package com.ctw.workstation.teammember.entity;

import com.ctw.workstation.rack.Status;
import com.ctw.workstation.team.entity.Team;
import jakarta.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="T_TEAM_MEMBER")
public class TeamMember {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(hidden=true)
    private UUID id;

    @Column(name="NAME", length = 20, nullable = false)
    private String name;

    @Column(name="CTW_ID", length = 20, nullable = false)
    private String ctw_id;

    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    @CreationTimestamp
    @Schema(hidden=true)
    private LocalDateTime createdAt;

    @Column(name = "MODIFIED_AT", nullable = false)
    @UpdateTimestamp
    @Schema(hidden=true)
    private LocalDateTime modifiedAt;

    @Column(name = "TEAM_ID")
    private UUID teamID;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TEAM_ID", updatable = false, insertable = false)
    @Schema(hidden=true)
    private Team team;

    public TeamMember() {}

    public TeamMember(String name, String ctw_id, Team team) {
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
        this.name = name;
        this.ctw_id = ctw_id;
        this.team = team;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCtw_id() {
        return ctw_id;
    }

    public void setCtw_id(String ctw_id) {
        this.ctw_id = ctw_id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public UUID getTeamID() {
        return teamID;
    }

    public void setTeamID(UUID teamID) {
        this.teamID = teamID;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }


}

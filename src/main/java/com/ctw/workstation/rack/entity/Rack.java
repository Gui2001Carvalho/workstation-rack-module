package com.ctw.workstation.rack.entity;

import com.ctw.workstation.rack.Status;
import com.ctw.workstation.team.TeamResource;
import com.ctw.workstation.team.entity.Team;
import jakarta.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="T_RACK")
public class Rack {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(hidden=true)
    private UUID id;

    @Column(name="SERIAL_NUMBER", length = 20, nullable = false)
    private String serialNumber;

    @Column(name="DEFAULT_LOCATION", length = 20, nullable = false)
    private String defaultLocation;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private Status status;

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


    public Rack() {
    }
/*
    public Rack(String serialNumber) {
        this.id = UUID.randomUUID();
        this.status = Status.AVAILABLE;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
        this.serialNumber = serialNumber;
    }

    public Rack(String serialNumber, String defaultLocation) {
        this(serialNumber);
        this.defaultLocation = defaultLocation;
    }

    public Rack(String serialNumber, String defaultLocation, Status status) {
        this(serialNumber, defaultLocation);
        this.status = status;
    }

    public Rack(String serialNumber, String defaultLocation, Status status, Team team) {
        this(serialNumber, defaultLocation, status);
        this.team = team;
        this.teamID = team.getId();
    }

     */

    public UUID getId() {
        return id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getDefaultLocation() {
        return defaultLocation;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public UUID getTeamID() {
        return teamID;
    }

    public Team getTeam() {
        return team;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setDefaultLocation(String defaultLocation) {
        this.defaultLocation = defaultLocation;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setTeamID(UUID teamID) {
        this.teamID = teamID;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}

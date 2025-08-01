package com.ctw.workstation.team.entity;

import com.ctw.workstation.team.DTOs.TeamInputDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Table(name="T_TEAM")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="NAME", length = 20, nullable = false)
    private String name;

    @Column(name="PRODUCT", length = 20, nullable = false)
    private String product;

    @Column(name="DEFAULT_LOCATION", length = 20)
    private String defaultLocation;

    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "MODIFIED_AT", nullable = false)
    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    public Team() {
    }

    public Team(TeamInputDTO dto) {
        this.name = dto.name;
        this.product = dto.product;
        this.defaultLocation = dto.defaultLocation;
    }


    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProduct() {
        return product;
    }

    public String getDefaultLocation() {
        return defaultLocation;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setDefaultLocation(String defaultLocation) {
        this.defaultLocation = defaultLocation;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}

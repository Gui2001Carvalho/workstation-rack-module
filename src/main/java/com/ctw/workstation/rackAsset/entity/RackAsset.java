package com.ctw.workstation.rackAsset.entity;

import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.team.entity.Team;
import jakarta.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.UUID;

@Entity
@Table(name="T_RACK_ASSET")
public class RackAsset {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(hidden=true)
    private UUID id;

    @Column(name="ASSET_TAG", length = 20, nullable = false)
    private String assetTag;

    @Column(name = "RACK_ID")
    private UUID rackId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RACK_ID", updatable = false, insertable = false)
    @Schema(hidden=true)
    private Rack rack;

    public UUID getId() {
        return id;
    }

    public String getAssetTag() {
        return assetTag;
    }

    public UUID getRackId() {
        return rackId;
    }

    public Rack getRack() {
        return rack;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public void setRack(Rack rack) {
        this.rack = rack;
    }

    public void setRackId(UUID rackId) {
        this.rackId = rackId;
    }
}

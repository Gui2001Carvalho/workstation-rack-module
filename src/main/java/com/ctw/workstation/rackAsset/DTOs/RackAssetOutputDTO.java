package com.ctw.workstation.rackAsset.DTOs;

import com.ctw.workstation.rackAsset.entity.RackAsset;
import com.ctw.workstation.team.entity.Team;

import java.util.UUID;

public class RackAssetOutputDTO {
    public UUID id;
    public String assetTag;
    public UUID rackId;

    public RackAssetOutputDTO() {}

    public RackAssetOutputDTO(RackAsset rackAsset) {
        this.id = rackAsset.getId();
        this.assetTag = rackAsset.getAssetTag();
        this.rackId = rackAsset.getRackId();
    }
}

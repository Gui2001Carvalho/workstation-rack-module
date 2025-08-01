package com.ctw.workstation.rackAsset.DTOs;

import com.ctw.workstation.rack.entity.Rack;
import jakarta.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.UUID;

public class RackAssetInputDTO {
    public String assetTag;
    public UUID rackId;
}

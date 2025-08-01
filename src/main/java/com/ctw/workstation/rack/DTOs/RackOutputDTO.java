package com.ctw.workstation.rack.DTOs;

import com.ctw.workstation.rack.Status;
import com.ctw.workstation.rack.entity.Rack;

import java.util.UUID;

public class RackOutputDTO {
    public UUID id;
    public String serialNumber;
    public String defaultLocation;
    public Status status;
    public UUID teamID;

    public RackOutputDTO() {}

    public RackOutputDTO(Rack entity) {
        this.id = entity.getId();
        this.serialNumber = entity.getSerialNumber();
        this.defaultLocation = entity.getDefaultLocation();
        this.status = entity.getStatus();
        this.teamID = entity.getTeamID();
    }
}

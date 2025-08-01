package com.ctw.workstation.rack.DTOs;

import com.ctw.workstation.rack.Status;

import java.util.UUID;

public class RackPatchDTO {
    public String serialNumber;
    public String defaultLocation;
    public Status status;
    public UUID teamID;
}

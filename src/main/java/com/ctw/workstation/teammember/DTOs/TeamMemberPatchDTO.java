package com.ctw.workstation.teammember.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class TeamMemberPatchDTO {
    public String name;
    public String ctw_id;
    public UUID teamID;
}

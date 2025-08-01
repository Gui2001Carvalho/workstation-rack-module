package com.ctw.workstation.teammember.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class TeamMemberInputDTO {

    @NotBlank
    @Size(max = 20)
    public String name;

    @NotBlank
    @Size(max = 20)
    public String ctw_id;

    public UUID teamID;

    public TeamMemberInputDTO(){}
}

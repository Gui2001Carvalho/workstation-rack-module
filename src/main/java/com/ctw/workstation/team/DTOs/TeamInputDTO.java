package com.ctw.workstation.team.DTOs;

import jakarta.validation.constraints.NotNull;

public class TeamInputDTO {
    @NotNull
    public String name;
    @NotNull
    public String product;
    public String defaultLocation;
}

package com.ctw.workstation.team;

import com.ctw.workstation.team.DTOs.TeamInputDTO;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class TeamServiceTest {

    @Inject
    TeamService teamService;

    @Test
    @DisplayName("Create a Team")
    void create_Team() {
        TeamInputDTO teamInputDTO = new TeamInputDTO();
        teamInputDTO.name = "Gui";
        teamInputDTO.product = "Gui2";
        teamInputDTO.defaultLocation = "Lisboa";

        //aqui deveria ser um objeto para os testes.
        Response response = teamService.addTeam(teamInputDTO);

        assertThat(teamInputDTO)
                .isNotNull()
                .hasNoNullFieldsOrProperties()
                .usingRecursiveComparison()
                .isEqualTo(teamInputDTO);

    }


}
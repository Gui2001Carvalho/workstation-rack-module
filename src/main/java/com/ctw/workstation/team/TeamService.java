package com.ctw.workstation.team;

import com.ctw.workstation.team.DTOs.TeamInputDTO;
import com.ctw.workstation.team.DTOs.TeamOutputDTO;
import com.ctw.workstation.team.DTOs.TeamPatchDTO;
import com.ctw.workstation.team.entity.Team;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class TeamService {

    private static final Logger log = Logger.getLogger(TeamResource.class);

    @Inject
    TeamRepository repository;

    @Inject
    TeamMapper mapper;

    public Response getTeams() {
        List<Team> teams = repository.listAll();
        List<TeamOutputDTO> dtos = teams.stream()
                .map(mapper::toDTO)
                .toList();
        log.info("Retrieved all teams");
        log.debugf("Team list: %s", dtos);
        return Response.ok(dtos).build();
    }

    public Response getTeamsbyId(UUID id) {
        Optional<Team> team = Optional.ofNullable(repository.findById(id));
        Optional<TeamOutputDTO> dto = team.map(mapper::toDTO);
        log.info("Retrieved team by id");
        log.debugf("Retrieved team by id: " + id);
        if (dto.isPresent()) {
            return Response.ok(dto.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    public Response getTeamsByLocation(String location) {
        List<Team> teams = repository.find("defaultLocation", location).list(); //Fazer um excepção?
        List<TeamOutputDTO> dtos = teams.stream().map(TeamOutputDTO::new).toList();
        log.infof("Retrieved %d teams by location '%s'", dtos.size(), location);
        log.debugf("Teams found: %s", dtos);
        return Response.ok(dtos).build();
    }

    public Response updateTeam(UUID id, TeamInputDTO dto) {
        Team updatedTeam = repository.findById(id);
        if (updatedTeam == null) {
            log.warnf("Team with ID %s not found", id);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        mapper.updateEntityFromDTO(dto, updatedTeam);
        log.infof("Team updated successfully: %s", id);
        log.debugf("Updated entity: %s", updatedTeam);
        return Response.ok(mapper.toDTO(updatedTeam)).build();
    }

    public Response addTeam(TeamInputDTO dto) {
        Team team = mapper.toEntity(dto);
        repository.persist(team);
        log.infof("Team created: %s", team.getId());
        log.debugf("Created entity: %s", team);
        return Response.status(Response.Status.CREATED).entity(mapper.toDTO(team)).build();
    }

    public Response removeTeam(UUID id) {
        log.debugf("Removing team with ID: %s", id);
        Team updatedTeam = repository.findById(id);
        if(updatedTeam != null) {
            repository.delete(updatedTeam);
            log.infof("Team removed successfully: %s", id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            log.warnf("Team with ID %s not found for removal", id);
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Team with ID " + id + " not found")
                    .build();
        }
    }

    public Response patchTeam(UUID id, TeamPatchDTO dto) {
        log.debugf("Patching team with ID: %s and data: %s", id, dto);
        Team existingTeam = repository.findById(id);
        if (existingTeam == null) {
            log.warnf("Team with ID %s not found", id);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        mapper.updateEntityFromPatchDTO(dto, existingTeam);
        log.infof("Team patched successfully: %s", id);
        log.debugf("Patched entity: %s", existingTeam);
        return Response.ok(mapper.toDTO(existingTeam)).build();
    }
}

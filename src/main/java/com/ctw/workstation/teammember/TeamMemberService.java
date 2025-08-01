package com.ctw.workstation.teammember;

import com.ctw.workstation.teammember.DTOs.TeamMemberInputDTO;
import com.ctw.workstation.teammember.DTOs.TeamMemberOutputDTO;
import com.ctw.workstation.teammember.DTOs.TeamMemberPatchDTO;
import com.ctw.workstation.teammember.entity.TeamMember;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class TeamMemberService {

    private static final Logger log = Logger.getLogger(TeamMemberService.class);

    @Inject
    TeamMemberRepository repository;

    @Inject
    TeamMemberMapper mapper;

    public Response getUsers() {
        List<TeamMember> teamMembers = repository.listAll();
        List<TeamMemberOutputDTO> dtos = teamMembers.stream().map(mapper::toDTO).toList();
        log.info("Retrieved all team members");
        log.debugf("TeamMembers list: %s", dtos);
        return Response.ok(dtos).build();
    }

    public Response getUsersbyId(UUID id) {
        Optional<TeamMember> teamMember = Optional.ofNullable(repository.findById(id));
        Optional<TeamMemberOutputDTO> dto = teamMember.map(mapper::toDTO);
        log.info("Retrieved team by id");
        log.debugf("Retrieved team by id: " + id);
        if (dto.isPresent()) {
            return Response.ok(dto.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    public Response addUser(TeamMemberInputDTO dto) {
        TeamMember teamMember = mapper.toEntity(dto);
        repository.persist(teamMember);
        log.infof("TeamMember created: %s", teamMember.getId());
        log.debugf("Created entity: %s", teamMember);
        return Response.status(Response.Status.CREATED).entity(mapper.toDTO(teamMember)).build();
    }

    public Response removeUser(UUID id) {
        log.debugf("Removing teamMember with ID: %s", id);
        TeamMember removeTeamMember = repository.findById(id);
        if(removeTeamMember != null) {
            repository.delete(removeTeamMember);
            log.infof("TeamMember removed successfully: %s", id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            log.warnf("TeamMember with ID %s not found for removal", id);
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("TeamMember with ID " + id + " not found")
                    .build();
        }
    }

    public Response patchUser(UUID id, TeamMemberPatchDTO dto) {
        log.debugf("Patching user with ID: %s and data: %s", id, dto);
        TeamMember existingUser = repository.findById(id);
        if (existingUser == null) {
            log.warnf("User with ID %s not found", id);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        mapper.updateEntityFromPatchDTO(dto, existingUser);
        log.infof("User patched successfully: %s", id);
        log.debugf("Patched entity: %s", existingUser);
        return Response.ok(mapper.toDTO(existingUser)).build();
    }




}

package com.ctw.workstation.teammember;

import com.ctw.workstation.teammember.DTOs.TeamMemberInputDTO;
import com.ctw.workstation.teammember.DTOs.TeamMemberOutputDTO;
import com.ctw.workstation.teammember.DTOs.TeamMemberPatchDTO;
import com.ctw.workstation.teammember.entity.TeamMember;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Path("/team-members")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class TeamMemberResource {

    @Inject
    TeamMemberService service;

    @GET
    public Response getUsers() {
        return service.getUsers();
    }

    @GET
    @Path("/{id}")
    public Response getUsersById(@PathParam("id") UUID id) {
        return service.getUsersbyId(id);
    }

    @Transactional
    @POST
    public Response addUser(@Valid TeamMemberInputDTO dto) {
        return service.addUser(dto);
    }

    @Transactional
    @DELETE
    @Path("/{id}")
    public Response removeUser(@PathParam("id") UUID id) {
        return service.removeUser(id);
    }

    @PATCH
    @Path("/{id}")
    @Transactional
    public Response patchUser(@PathParam("id") UUID id, TeamMemberPatchDTO dto) {
        return service.patchUser(id, dto);
    }
}

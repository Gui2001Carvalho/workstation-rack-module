package com.ctw.workstation.team;

import com.ctw.workstation.team.DTOs.TeamInputDTO;
import com.ctw.workstation.team.DTOs.TeamPatchDTO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.util.UUID;

@Path("/teams")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class TeamResource {

    private static final Logger log = Logger.getLogger(TeamResource.class);

    @Inject
    TeamService service;

    @GET
    public Response getTeams() {
        return service.getTeams();
    }

    @GET
    @Path("/{id}")
    public Response getTeamsById(@PathParam("id") UUID id) {
        return service.getTeamsbyId(id);
    }

    @GET
    @Path("/location")
    public Response getTeamsByLocation(@QueryParam("defaultLocation") String location) {
        return service.getTeamsByLocation(location);
    }

    @Transactional
    @PUT
    @Path("/{id}")
    public Response updateTeam(@PathParam("id") UUID id, TeamInputDTO dto) {
        return service.updateTeam(id, dto);
    }

    @Transactional
    @POST
    public Response addTeam(@Valid TeamInputDTO dto) {
        return service.addTeam(dto);
    }

    @Transactional
    @DELETE
    @Path("/{id}")
    public Response removeTeam(@PathParam("id") UUID id) {
        return service.removeTeam(id);
    }

    @PATCH
    @Path("/{id}")
    @Transactional
    public Response patchTeam(@PathParam("id") UUID id, TeamPatchDTO dto) {
        return service.patchTeam(id, dto);
    }
}

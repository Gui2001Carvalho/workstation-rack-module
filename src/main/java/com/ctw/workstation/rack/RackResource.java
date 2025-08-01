package com.ctw.workstation.rack;

import com.ctw.workstation.rack.DTOs.RackInputDTO;
import com.ctw.workstation.rack.DTOs.RackPatchDTO;
import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.team.DTOs.TeamInputDTO;
import com.ctw.workstation.team.DTOs.TeamPatchDTO;
import com.ctw.workstation.team.TeamRepository;
import com.ctw.workstation.team.TeamService;
import com.ctw.workstation.team.entity.Team;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Path("/racks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class RackResource {

    @Inject
    RackService service;

    @GET
    public Response getRacks() {
        return service.getRacks();
    }

    @GET
    @Path("/{id}")
    public Response getRacksById(@PathParam("id") UUID id) {
        return service.getRackbyId(id);
    }

    @Transactional
    @POST
    public Response addRack(@Valid RackInputDTO dto) {
        return service.addRack(dto);
    }

    @Transactional
    @DELETE
    @Path("/{id}")
    public Response removeRack(@PathParam("id") UUID id) {
        return  service.removeRack(id);
    }

    @PATCH
    @Path("/{id}")
    @Transactional
    public Response patchRack(@PathParam("id") UUID id, RackPatchDTO dto) {
        return service.patchRack(id, dto);
    }
}

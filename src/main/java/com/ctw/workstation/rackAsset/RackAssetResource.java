package com.ctw.workstation.rackAsset;

import com.ctw.workstation.rackAsset.DTOs.RackAssetInputDTO;
import com.ctw.workstation.rackAsset.DTOs.RackAssetPatchDTO;
import com.ctw.workstation.rackAsset.entity.RackAsset;
import com.ctw.workstation.team.DTOs.TeamPatchDTO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Path("/rackAsset")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class RackAssetResource {

    @Inject
    RackAssetService service;

    @GET
    public Response getRackAssets() {
        return service.getRackAssets();
    }

    @GET
    @Path("/{id}")
    public Response getRackAssetsById(@PathParam("id") UUID id) {
        return service.getRackAssetsById(id);
    }

    @Transactional
    @POST
    public Response addRackAsset(RackAssetInputDTO dto) {
        return service.addRackAsset(dto);
    }

    @Transactional
    @DELETE
    @Path("/{id}")
    public Response removeRackAssets(@PathParam("id") UUID id) {
        return service.removeRackAsset(id);
    }

    @PATCH
    @Path("/{id}")
    @Transactional
    public Response patchRackAsset(@PathParam("id") UUID id, RackAssetPatchDTO dto) {
        return service.patchRackAsset(id, dto);
    }
}

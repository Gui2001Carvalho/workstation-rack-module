package com.ctw.workstation.rackAsset;

import com.ctw.workstation.rackAsset.DTOs.RackAssetInputDTO;
import com.ctw.workstation.rackAsset.DTOs.RackAssetOutputDTO;
import com.ctw.workstation.rackAsset.DTOs.RackAssetPatchDTO;
import com.ctw.workstation.rackAsset.entity.RackAsset;
import com.ctw.workstation.team.DTOs.TeamInputDTO;
import com.ctw.workstation.team.DTOs.TeamOutputDTO;
import com.ctw.workstation.team.DTOs.TeamPatchDTO;
import com.ctw.workstation.team.TeamResource;
import com.ctw.workstation.team.entity.Team;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class RackAssetService {

    private static final Logger log = Logger.getLogger(TeamResource.class);

    @Inject
    RackAssetRepository repository;

    @Inject
    RackAssetMapper mapper;

    public Response getRackAssets() {
        List<RackAsset> rackAssets = repository.listAll();
        List<RackAssetOutputDTO> dtos = rackAssets.stream()
                .map(mapper::toDTO)
                .toList();
        log.info("Retrieved all rackAssets");
        log.debugf("RackAssets list: %s", dtos);
        return Response.ok(dtos).build();
    }

    public Response getRackAssetsById(UUID id) {
        Optional<RackAsset> rackAsset = Optional.ofNullable(repository.findById(id));
        Optional<RackAssetOutputDTO> dto = rackAsset.map(mapper::toDTO);
        log.info("Retrieved RackAsset by id");
        log.debugf("Retrieved rackAsset by id: " + id);
        if (dto.isPresent()) {
            return Response.ok(dto.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    public Response addRackAsset(RackAssetInputDTO dto) {
        RackAsset rackAsset = mapper.toEntity(dto);
        repository.persist(rackAsset);
        log.infof("RackAsset created: %s", rackAsset.getId());
        log.debugf("Created entity: %s", rackAsset);
        return Response.status(Response.Status.CREATED).entity(mapper.toDTO(rackAsset)).build();
    }

    public Response removeRackAsset(UUID id) {
        log.debugf("Removing RackAsset with ID: %s", id);
        RackAsset rackAsset = repository.findById(id);
        if(rackAsset != null) {
            repository.delete(rackAsset);
            log.infof("RackAsset removed successfully: %s", id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            log.warnf("RackAsset with ID %s not found for removal", id);
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("RackAsset with ID " + id + " not found")
                    .build();
        }
    }

    public Response patchRackAsset(UUID id, RackAssetPatchDTO dto) {
        log.debugf("Patching rackAsset with ID: %s and data: %s", id, dto);
        RackAsset existingRackAsset = repository.findById(id);
        if (existingRackAsset == null) {
            log.warnf("RackAsset with ID %s not found", id);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        mapper.updateEntityFromPatchDTO(dto, existingRackAsset);
        log.infof("RackAsset patched successfully: %s", id);
        log.debugf("Patched entity: %s", existingRackAsset);
        return Response.ok(mapper.toDTO(existingRackAsset)).build();
    }
}

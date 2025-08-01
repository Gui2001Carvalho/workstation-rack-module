package com.ctw.workstation.rack;

import com.ctw.workstation.rack.DTOs.RackInputDTO;
import com.ctw.workstation.rack.DTOs.RackOutputDTO;
import com.ctw.workstation.rack.DTOs.RackPatchDTO;
import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.team.DTOs.TeamOutputDTO;
import com.ctw.workstation.team.DTOs.TeamPatchDTO;
import com.ctw.workstation.team.TeamResource;
import com.ctw.workstation.team.entity.Team;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@ApplicationScoped
public class RackService {

    private static final Logger log = Logger.getLogger(TeamResource.class);

    @Inject
    RackRepository repository;

    @Inject
    RackMapper mapper;

    public Response getRacks() {
        List<Rack> racks = repository.listAll();
        List<RackOutputDTO> dtos = racks.stream()
                .map(mapper::toDto)
                .toList();
        log.info("Retrieved all racks");
        log.debugf("Rack list: %s", dtos);
        return Response.ok(dtos).build();
    }

    public Response getRackbyId(UUID id) {
        Optional<Rack> rack = Optional.ofNullable(repository.findById(id));
        Optional<RackOutputDTO> dto = rack.map(mapper::toDto);
        log.info("Retrieved rack by id");
        log.debugf("Retrieved rack by id: " + id);
        if (dto.isPresent()) {
            return Response.ok(dto.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    public Response addRack(RackInputDTO dto) {
        Rack rack = mapper.toEntity(dto);
        repository.persist(rack);
        log.infof("Team created: %s", rack.getId());
        log.debugf("Created entity: %s", rack);
        return Response.status(Response.Status.CREATED).entity(mapper.toDto(rack)).build();
    }

    public Response removeRack(UUID id) {
        log.debugf("Removing Rack with ID: %s", id);
        Rack updatedRack = repository.findById(id);
        if(updatedRack != null) {
            repository.delete(updatedRack);
            log.infof("Rack removed successfully: %s", id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            log.warnf("Rack with ID %s not found for removal", id);
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Rack with ID " + id + " not found")
                    .build();
        }
    }

    public Response patchRack(UUID id, RackPatchDTO dto) {
        log.debugf("Patching Rack with ID: %s and data: %s", id, dto);
        Rack existingRack = repository.findById(id);
        if (existingRack == null) {
            log.warnf("Rack with ID %s not found", id);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        mapper.updateEntityFromPatchDTO(dto, existingRack);
        log.infof("Rack patched successfully: %s", id);
        log.debugf("Patched entity: %s", existingRack);
        return Response.ok(mapper.toDto(existingRack)).build();
    }


}

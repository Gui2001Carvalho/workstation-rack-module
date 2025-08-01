package com.ctw.workstation.rack;

import com.ctw.workstation.rack.DTOs.RackInputDTO;
import com.ctw.workstation.rack.DTOs.RackOutputDTO;
import com.ctw.workstation.rack.DTOs.RackPatchDTO;
import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.teammember.DTOs.TeamMemberInputDTO;
import com.ctw.workstation.teammember.DTOs.TeamMemberPatchDTO;
import com.ctw.workstation.teammember.entity.TeamMember;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RackMapper {

    RackMapper INSTANCE = Mappers.getMapper(RackMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    Rack toEntity(RackInputDTO dto);

    RackOutputDTO toDto(Rack entity);

    void updateEntityFromPatchDTO(RackPatchDTO dto, @MappingTarget Rack entity);
}


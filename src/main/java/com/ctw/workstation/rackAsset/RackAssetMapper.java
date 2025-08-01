package com.ctw.workstation.rackAsset;

import com.ctw.workstation.rackAsset.DTOs.RackAssetInputDTO;
import com.ctw.workstation.rackAsset.DTOs.RackAssetOutputDTO;
import com.ctw.workstation.rackAsset.DTOs.RackAssetPatchDTO;
import com.ctw.workstation.rackAsset.entity.RackAsset;
import com.ctw.workstation.team.DTOs.TeamPatchDTO;
import com.ctw.workstation.team.TeamMapper;
import com.ctw.workstation.team.entity.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RackAssetMapper {

    RackAssetMapper teamMapper = Mappers.getMapper(RackAssetMapper.class);

    @Mapping(target = "id", ignore = true)
    RackAsset toEntity(RackAssetInputDTO dto);

    RackAssetOutputDTO toDTO(RackAsset entity);

    void updateEntityFromPatchDTO(RackAssetPatchDTO dto, @MappingTarget RackAsset entity);
}

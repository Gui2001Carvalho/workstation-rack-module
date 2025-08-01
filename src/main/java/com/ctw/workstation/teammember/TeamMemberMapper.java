package com.ctw.workstation.teammember;

import com.ctw.workstation.team.DTOs.TeamInputDTO;
import com.ctw.workstation.team.DTOs.TeamOutputDTO;
import com.ctw.workstation.team.DTOs.TeamPatchDTO;
import com.ctw.workstation.team.TeamMapper;
import com.ctw.workstation.team.entity.Team;
import com.ctw.workstation.teammember.DTOs.TeamMemberInputDTO;
import com.ctw.workstation.teammember.DTOs.TeamMemberOutputDTO;
import com.ctw.workstation.teammember.DTOs.TeamMemberPatchDTO;
import com.ctw.workstation.teammember.entity.TeamMember;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TeamMemberMapper {

    TeamMemberMapper INSTANCE = Mappers.getMapper(TeamMemberMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    TeamMember toEntity(TeamMemberInputDTO dto);

    TeamMemberOutputDTO toDTO(TeamMember entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    void updateEntityFromDTO(TeamMemberInputDTO dto, @MappingTarget TeamMember entity);

    void updateEntityFromPatchDTO(TeamMemberPatchDTO dto, @MappingTarget TeamMember entity);
}

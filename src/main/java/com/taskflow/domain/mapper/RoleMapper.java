package com.taskflow.domain.mapper;

import com.taskflow.domain.dto.request.role.RoleRequestDto;
import com.taskflow.domain.dto.response.role.RoleResponseDto;
import com.taskflow.domain.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleResponseDto toDto(Role user);
    Role toUser(RoleRequestDto userDto);
}

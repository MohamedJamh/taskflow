package com.taskflow.domain.mapper;

import com.taskflow.domain.dto.request.user.UserRequestDto;
import com.taskflow.domain.dto.response.user.UserResponseDto;
import com.taskflow.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserResponseDto toDto(User user);
    User toUser(UserRequestDto userDto);
}

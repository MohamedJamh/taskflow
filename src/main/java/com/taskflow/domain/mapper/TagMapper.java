package com.taskflow.domain.mapper;

import com.taskflow.domain.dto.request.TagRequestDto;
import com.taskflow.domain.dto.response.TagResponseDto;
import com.taskflow.domain.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);
    Tag toTag(TagRequestDto tagDto);
    TagResponseDto toDto(Tag tagDto);
}

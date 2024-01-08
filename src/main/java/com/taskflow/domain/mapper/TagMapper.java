package com.taskflow.domain.mapper;

import com.taskflow.domain.dto.request.tag.TagRequestDto;
import com.taskflow.domain.dto.response.tag.TagResponseDto;
import com.taskflow.domain.entity.Tag;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    Tag toTag(TagRequestDto tagDto);

    TagResponseDto toDto(Tag tagDto);

    TagRequestDto toDto1(Tag tag);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Tag partialUpdate(TagRequestDto tagRequestDto, @MappingTarget Tag tag);
}

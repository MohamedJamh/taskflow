package com.taskflow.web.rest;

import com.taskflow.domain.dto.request.tag.TagRequestDto;
import com.taskflow.domain.dto.response.tag.TagResponseDto;
import com.taskflow.domain.mapper.TagMapper;
import com.taskflow.exception.customexceptions.ValidationException;
import com.taskflow.service.TagService;
import com.taskflow.utils.Response;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/tags")
public class TagRest {

    private final TagService tagService;
    private final TagMapper tagMapper;
    @GetMapping
    public ResponseEntity<Response<List<TagResponseDto>>> getAllTags()
    {
        Response<List<TagResponseDto>> response = new Response<>();
        response.setResult(
                tagService.findAll()
                    .stream()
                    .map(tagMapper::toDto)
                    .toList()
        );
        response.setMessage("Tags retrieved successfully");
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<Response<TagResponseDto>> createTag(
            @RequestBody TagRequestDto tagResponseDto
    ) throws ValidationException
    {
        Response<TagResponseDto> response = new Response<>();
        response.setResult(
                tagMapper.toDto(tagService.createTag(tagMapper.toTag(tagResponseDto)))
        );
        response.setMessage("Tag created successfully");
        return ResponseEntity.ok().body(response);
    }
}

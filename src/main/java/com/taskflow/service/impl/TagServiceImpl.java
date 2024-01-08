package com.taskflow.service.impl;

import com.taskflow.domain.entity.Tag;
import com.taskflow.exception.customexceptions.ValidationException;
import com.taskflow.repository.TagRepository;
import com.taskflow.service.TagService;
import com.taskflow.utils.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@AllArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public Tag createTag(Tag tag) throws ValidationException {
        if(tagRepository.findByName(tag.getName()).isPresent()){
            throw new ValidationException(
                    List.of(
                            ErrorMessage.builder()
                                    .field("name")
                                    .message("Tag with this name already exists")
                                    .build()
                    )
            );
        }
        return tagRepository.save(tag);
    }
}

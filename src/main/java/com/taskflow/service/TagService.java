package com.taskflow.service;

import com.taskflow.domain.entity.Tag;
import com.taskflow.exception.customexceptions.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TagService {
    List<Tag> findAll();
    Tag createTag(Tag tag) throws ValidationException;
}

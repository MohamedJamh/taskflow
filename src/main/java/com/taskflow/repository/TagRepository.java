package com.taskflow.repository;

import com.taskflow.domain.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long>{
    @Query("SELECT t FROM Tag t WHERE t.name = :name")
    Optional<Tag> findByName(String name);
}

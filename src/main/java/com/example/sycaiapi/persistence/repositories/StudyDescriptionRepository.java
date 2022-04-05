package com.example.sycaiapi.persistence.repositories;

import com.example.sycaiapi.persistence.entity.StudyDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StudyDescriptionRepository extends JpaRepository<StudyDescription, Integer> {
    @Query(value = "SELECT description from study_descriptions where id = ?1", nativeQuery = true)
    Optional<String> findStudyDescription(Integer integer);

    @Query(value = "SELECT id from study_descriptions where description = ?1", nativeQuery = true)
    Optional<Integer> findStudyDescriptionId(String description);
}

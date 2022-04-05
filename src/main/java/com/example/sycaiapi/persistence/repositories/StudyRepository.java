package com.example.sycaiapi.persistence.repositories;

import com.example.sycaiapi.persistence.entity.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StudyRepository extends JpaRepository<Study, String> {
    @Query(value = "SELECT count(*) from studies", nativeQuery = true)
    Integer getTotalStudies();

    @Query(value = "SELECT count(*) from studies where final_diagnosis_id = ?1",
            nativeQuery = true)
    Integer getLesionCounter(Integer id);

    Optional<Study> findStudyByPatientId(String patId);
}

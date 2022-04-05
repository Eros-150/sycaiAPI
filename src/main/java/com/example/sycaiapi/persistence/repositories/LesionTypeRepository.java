package com.example.sycaiapi.persistence.repositories;

import com.example.sycaiapi.persistence.entity.LesionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LesionTypeRepository extends JpaRepository<LesionType, Integer> {
    @Query(value = "SELECT type from lesion_types where id = ?1", nativeQuery = true)
    Optional<String> findLesionType(Integer integer);

    @Query(value = "SELECT id from lesion_types where type = ?1", nativeQuery = true)
    Optional<Integer> findLesionTypeId(String type);

}

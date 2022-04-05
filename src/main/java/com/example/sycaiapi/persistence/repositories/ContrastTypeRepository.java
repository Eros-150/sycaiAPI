package com.example.sycaiapi.persistence.repositories;

import com.example.sycaiapi.persistence.entity.ContrastType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ContrastTypeRepository extends JpaRepository<ContrastType, Integer> {
    @Query(value = "SELECT type from contrast_types where id = ?1", nativeQuery = true)
    Optional<String> findContrastType(Integer integer);

    @Query(value = "SELECT id from contrast_types where type = ?1", nativeQuery = true)
    Optional<Integer> findContrastTypeId(String type);
}

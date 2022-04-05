package com.example.sycaiapi.persistence.repositories;

import com.example.sycaiapi.persistence.entity.Labeler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LabelerRepository extends JpaRepository<Labeler, Integer> {
    @Query(value = "SELECT name from labelers where id = ?1", nativeQuery = true)
    Optional<String> findLabelerName(Integer integer);

    @Query(value = "SELECT id from labelers where name = ?1", nativeQuery = true)
    Optional<Integer> findLabelerNameId(String name);
}

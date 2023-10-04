package com.project.createivewriting.repository;

import com.project.createivewriting.model.entity.DairyEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DairyEntryRepository extends JpaRepository<DairyEntry,Integer> {
}

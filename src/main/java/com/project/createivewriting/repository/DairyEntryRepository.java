package com.project.createivewriting.repository;

import com.project.createivewriting.model.entity.DairyEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DairyEntryRepository extends JpaRepository<DairyEntry,Integer> {

    String EXISTS_BY_DATE = """
            Select * from dairy_entry where date = :date
            """;

    @Query(value = EXISTS_BY_DATE,nativeQuery = true)
    DairyEntry findByDate(LocalDate date);

}

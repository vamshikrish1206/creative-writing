package com.project.createivewriting.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "dairy_entry")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class DairyEntry implements Serializable {

    public static final Long serialUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "dairy_entry_generator")
    @SequenceGenerator(name = "dairy_entry_generator", sequenceName = "public.dairy_entry_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "date")
    private LocalDate entryDate;

    @Column(name = "entry")
    private String content;

}

package com.project.createivewriting.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class DairyEntryDto {

    private Integer id;
    private LocalDate entryDate;
    private String content;
}

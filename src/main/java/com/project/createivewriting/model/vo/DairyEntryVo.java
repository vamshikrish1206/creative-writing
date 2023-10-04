package com.project.createivewriting.model.vo;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class DairyEntryVo {

    private Integer id;
    private LocalDate entryDate;
    private String content;
}

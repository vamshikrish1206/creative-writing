package com.project.createivewriting.mappers.internal;

import com.project.createivewriting.model.entity.DairyEntry;
import com.project.createivewriting.model.vo.DairyEntryVo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DairyEntryVoMapper {

    DairyEntryVo convert(DairyEntry dairyEntry);

    List<DairyEntryVo> convert(List<DairyEntry> dairyEnties);

    DairyEntry convert(DairyEntryVo dairyEntryVo);
}

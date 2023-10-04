package com.project.createivewriting.mappers.external;

import com.project.createivewriting.model.dto.DairyEntryDto;
import com.project.createivewriting.model.entity.DairyEntry;
import com.project.createivewriting.model.vo.DairyEntryVo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DairyEntryMapper {

    DairyEntryDto map(DairyEntryVo dairyEntryVo);

    List<DairyEntryDto> map(List<DairyEntryVo> dairyEntryVo);

    DairyEntryVo convert(DairyEntryDto dairyEntryVo);
}

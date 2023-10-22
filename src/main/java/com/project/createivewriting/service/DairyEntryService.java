package com.project.createivewriting.service;

import com.project.createivewriting.exception.BadRequestException;
import com.project.createivewriting.exception.DataNotFoundException;
import com.project.createivewriting.mappers.external.DairyEntryMapper;
import com.project.createivewriting.mappers.internal.DairyEntryVoMapper;
import com.project.createivewriting.model.entity.DairyEntry;
import com.project.createivewriting.model.vo.DairyEntryVo;
import com.project.createivewriting.repository.DairyEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DairyEntryService {

    private final DairyEntryRepository repository;

    private final DairyEntryVoMapper mapper;

    public Page<DairyEntry> getAllEntries(Integer pageNo, Integer pageSize){
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return Optional.of(repository.findAll(pageable))
                .orElseThrow(()->new DataNotFoundException("DAIRY_ENTRIES_NOT_FOUND"));
    }

    public void saveEntry(DairyEntryVo dairyEntryVo){
        repository.save(mapper.convert(dairyEntryVo));
    }

    public void modifyEntry(DairyEntryVo dairyEntryVo){
        repository.save(mapper.convert(dairyEntryVo));
    }

    public void deleteEntry(Integer id){
        if(!repository.existsById(id)){
            throw new BadRequestException("DARY_ID_NOT_FOUND");
        }
        repository.deleteById(id);
    }

    public DairyEntryVo findById(Integer id){
        if(!repository.existsById(id)){
            throw new BadRequestException("DARY_ID_NOT_FOUND");
        }
        return mapper.convert(repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("DAIRY_ENTRY_NOT_FOUND")));
    }

    public Boolean existsByDate(LocalDate date){
        DairyEntry entry = repository.findByDate(date);
        return !Objects.isNull(entry);
    }
}

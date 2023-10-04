package com.project.createivewriting.service;

import com.project.createivewriting.exception.BadRequestException;
import com.project.createivewriting.exception.DataNotFoundException;
import com.project.createivewriting.mappers.external.DairyEntryMapper;
import com.project.createivewriting.mappers.internal.DairyEntryVoMapper;
import com.project.createivewriting.model.vo.DairyEntryVo;
import com.project.createivewriting.repository.DairyEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DairyEntryService {

    private final DairyEntryRepository repository;

    private final DairyEntryVoMapper mapper;

    public List<DairyEntryVo> getAllEntries(){
        return mapper.convert(Optional.of(repository.findAll())
                .orElseThrow(()->new DataNotFoundException("DAIRY_ENTRIES_NOT_FOUND")));
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
}

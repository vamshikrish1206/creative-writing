package com.project.createivewriting.controller;

import com.project.createivewriting.exception.DataNotFoundException;
import com.project.createivewriting.mappers.external.DairyEntryMapper;
import com.project.createivewriting.model.dto.DairyEntryDto;
import com.project.createivewriting.model.vo.DairyEntryVo;
import com.project.createivewriting.service.DairyEntryService;
import lombok.RequiredArgsConstructor;
import org.hibernate.dialect.PostgreSQLJsonPGObjectJsonbType;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dairy")
public class DairyEntryController {

    private final DairyEntryService service;

    private final DairyEntryMapper mapper;
    @GetMapping(path = "/dairy_entries",produces = "application/json")
    public String getDairyEntries(Model model){
        List<DairyEntryVo> dairyEntryVos = service.getAllEntries()
                .stream().sorted(Comparator.comparing(DairyEntryVo::getId)).toList();
        model.addAttribute("dairyEntriesList",mapper.map(dairyEntryVos));
        return "dairy-entries-list";
    }

    @PostMapping(path = "/dairy_entry",consumes = "application/json")
    public ResponseEntity<Void> createEntry(@RequestBody DairyEntryDto dairyEntryDto){
        service.saveEntry(mapper.convert(dairyEntryDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(path = "/dairy_entry",consumes = "application/json")
    public ResponseEntity<Void> modifyDairyEntry(@RequestBody DairyEntryDto dairyEntryDto){
        service.modifyEntry(mapper.convert(dairyEntryDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping(path = "/dairy_entry/{id}")
    public ResponseEntity<Void> deleteDairyEntry(@PathVariable(name = "id") Integer id){
        service.deleteEntry(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/dairy_entry/{id}",produces = "application/json")
    public ResponseEntity<DairyEntryDto> findById(@PathVariable(name = "id") Integer id){
        return new ResponseEntity<DairyEntryDto>(mapper.map(service.findById(id)), HttpStatus.OK);
    }
}

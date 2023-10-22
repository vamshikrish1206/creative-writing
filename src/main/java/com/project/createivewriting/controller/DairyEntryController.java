package com.project.createivewriting.controller;

import com.project.createivewriting.exception.DataNotFoundException;
import com.project.createivewriting.mappers.external.DairyEntryMapper;
import com.project.createivewriting.mappers.internal.DairyEntryVoMapper;
import com.project.createivewriting.model.dto.DairyEntryDto;
import com.project.createivewriting.model.entity.DairyEntry;
import com.project.createivewriting.model.vo.DairyEntryVo;
import com.project.createivewriting.service.DairyEntryService;
import lombok.RequiredArgsConstructor;
import org.hibernate.dialect.PostgreSQLJsonPGObjectJsonbType;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dairy")
public class DairyEntryController {

    public static final int PAGE_SIZE = 10;
    private final DairyEntryService service;

    private final DairyEntryMapper mapper;

    private final DairyEntryVoMapper voMapper;
    @GetMapping(path = "/dairy_entries/{pageNo}",produces = "application/json")
    public String getDairyEntries(@PathVariable(value = "pageNo") Integer pageNo,Model model){
        Page<DairyEntry> dairyEntryPage = service.getAllEntries(pageNo, PAGE_SIZE);
        List<DairyEntryVo> dairyEntries = voMapper.convert(dairyEntryPage.getContent());
        List<DairyEntryVo> dairyEntryVos = dairyEntries.stream()
                .sorted(Comparator.comparing(DairyEntryVo::getEntryDate).reversed()).toList();


        model.addAttribute("dairyEntriesList",mapper.map(dairyEntryVos));
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",dairyEntryPage.getTotalPages());

        return "entries-list";
    }

    @PostMapping(path = "/dairy_entry")
    public String createEntry(@ModelAttribute(name = "dairyEntry") DairyEntryDto dairyEntryDto){
        service.saveEntry(mapper.convert(dairyEntryDto));
        return "redirect:/dairy/dairy_entries/1";
    }

    @PutMapping(path = "/dairy_entry")
    public String modifyDairyEntry(@RequestBody DairyEntryDto dairyEntryDto){
        service.modifyEntry(mapper.convert(dairyEntryDto));
        return "";
    }


    @GetMapping(path = "/dairy_entry/{id}")
    public String deleteDairyEntry(@PathVariable(name = "id") Integer id,@RequestParam(name = "pageNo") Integer pageNo){
        service.deleteEntry(id);
        return "redirect:/dairy/dairy_entries/"+pageNo.toString();
    }

    @GetMapping(path = "/dairy_entry/{id}",produces = "application/json")
    public String findById(@PathVariable(name = "id") Integer id){
        DairyEntryDto dairyEntryDto = mapper.map(service.findById(id));
        return "";
    }

    @GetMapping(path = "/dairy_new_entry")
    public String getNewEntryPage(Model model){
        DairyEntryDto dairyEntry = new DairyEntryDto();
        model.addAttribute("dairyEntry",dairyEntry);
        return "create-entry";
    }
}

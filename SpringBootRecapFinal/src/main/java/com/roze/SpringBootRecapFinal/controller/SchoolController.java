package com.roze.SpringBootRecapFinal.controller;

import com.roze.SpringBootRecapFinal.domain.School;
import com.roze.SpringBootRecapFinal.dto.SchoolRequestDto;
import com.roze.SpringBootRecapFinal.dto.SchoolResponseDto;
import com.roze.SpringBootRecapFinal.repository.SchoolRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/schools")
public class SchoolController {
    private final SchoolRepository schoolRepository;

    public SchoolController(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @PostMapping
    public SchoolResponseDto create(@RequestBody SchoolRequestDto requestDto) {
        School school = toSchool(requestDto);
        School savedSchool = schoolRepository.save(school);
        return toSchoolResponseDto(savedSchool);
    }

    @GetMapping
    public List<SchoolResponseDto> findAllSchools() {
        return schoolRepository.findAll().stream().map(this::toSchoolResponseDto).collect(Collectors.toList());
    }

    private School toSchool(SchoolRequestDto requestDto) {
        School school = new School();
        school.setName(requestDto.name());
        return school;

    }

    private SchoolResponseDto toSchoolResponseDto(School school) {
        SchoolResponseDto responseDto = new SchoolResponseDto(school.getId(), school.getName());
        return responseDto;
    }
}

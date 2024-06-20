package com.roze.SpringBootRecapFinal.controller;

import com.roze.SpringBootRecapFinal.domain.School;
import com.roze.SpringBootRecapFinal.repository.SchoolRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schools")
public class SchoolController {
    private final SchoolRepository schoolRepository;

    public SchoolController(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @PostMapping
    public School create(@RequestBody School school) {
        return schoolRepository.save(school);
    }

    @GetMapping
    public List<School> findAllSchools() {
        return schoolRepository.findAll();
    }
}

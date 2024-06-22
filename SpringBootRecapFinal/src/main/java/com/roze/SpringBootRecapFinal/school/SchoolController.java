package com.roze.SpringBootRecapFinal.school;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schools")
public class SchoolController {
    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping
    public SchoolResponseDto create(@RequestBody SchoolRequestDto requestDto) {
        return schoolService.create(requestDto);
    }

    @GetMapping
    public List<SchoolResponseDto> findAllSchools() {
        return schoolService.findAllSchools();
    }


}

package com.roze.SpringBootRecapFinal.school;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {
    private final SchoolMapper schoolMapper;
    private final SchoolRepository schoolRepository;


    public SchoolService(SchoolMapper schoolMapper, SchoolRepository schoolRepository) {
        this.schoolMapper = schoolMapper;
        this.schoolRepository = schoolRepository;
    }

    public SchoolResponseDto create(@RequestBody SchoolRequestDto requestDto) {
        School school = schoolMapper.toSchool(requestDto);
        School savedSchool = schoolRepository.save(school);
        return schoolMapper.toSchoolResponseDto(savedSchool);
    }

    public List<SchoolResponseDto> findAllSchools() {
        return schoolRepository.findAll().stream().map(schoolMapper::toSchoolResponseDto)
                .collect(Collectors.toList());
    }
}

package com.roze.SpringBootRecapFinal.school;

import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {
    public School toSchool(SchoolRequestDto requestDto) {
        School school = new School();
        school.setName(requestDto.name());
        return school;

    }

    public SchoolResponseDto toSchoolResponseDto(School school) {
        SchoolResponseDto responseDto = new SchoolResponseDto(school.getId(), school.getName());
        return responseDto;
    }
}

package com.roze.SpringBootRecapFinal.repository;

import com.roze.SpringBootRecapFinal.domain.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School,Integer> {
}

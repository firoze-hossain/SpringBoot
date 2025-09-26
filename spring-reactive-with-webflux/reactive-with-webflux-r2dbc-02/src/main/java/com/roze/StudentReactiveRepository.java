package com.roze;

import com.roze.model.Student;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface StudentReactiveRepository extends ReactiveCrudRepository<Student, Integer> {
    @Query("select sleep(1), s.id,s.name,s.age from student s")
    Flux<Student> findAllStudents();
}

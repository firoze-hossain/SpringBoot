package com.roze.service;

import com.roze.base.BaseResponse;
import com.roze.base.PaginatedResponse;
import com.roze.dto.StudentRequest;
import com.roze.dto.StudentResponse;
import com.roze.entity.Student;
import com.roze.exception.StudentNotFoundException;
import com.roze.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final DatabaseClient databaseClient;

    public Mono<BaseResponse<StudentResponse>> create(StudentRequest request) {
        return studentRepository.save(request.toEntity())
                .map(StudentResponse::toResponse)
                .map(resp -> BaseResponse.<StudentResponse>builder()
                        .success(true)
                        .message("Student created successfully")
                        .data(resp)
                        .timestamp(Instant.now())
                        .statusCode(HttpStatus.CREATED.value())
                        .build());
    }

    public Mono<BaseResponse<StudentResponse>> update(Long id, StudentRequest request) {
        return studentRepository.findById(id)
                .flatMap(existing -> studentRepository.save(request.toUpdateEntity(id)))
                .map(StudentResponse::toResponse)
                .map(resp -> BaseResponse.<StudentResponse>builder()
                        .success(true)
                        .message("Student updated successfully")
                        .data(resp)
                        .timestamp(Instant.now())
                        .statusCode(HttpStatus.OK.value())
                        .build())
                .switchIfEmpty(Mono.error(new StudentNotFoundException(id)));
    }

    public Mono<BaseResponse<StudentResponse>> getStudent(Long id) {
        return studentRepository.findById(id)
                .switchIfEmpty(Mono.error(new StudentNotFoundException(id)))
                .map(StudentResponse::toResponse)
                .map(resp -> BaseResponse.<StudentResponse>builder()
                        .success(true)
                        .message("Student fetched successfully")
                        .data(resp)
                        .timestamp(Instant.now())
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }

    public Mono<BaseResponse<Void>> delete(Long id) {
        return studentRepository.findById(id)
                .flatMap(student -> studentRepository.delete(student)
                        .thenReturn(BaseResponse.<Void>builder()
                                .success(true)
                                .message("Student deleted successfully")
                                .data(null)
                                .timestamp(Instant.now())
                                .statusCode(HttpStatus.NO_CONTENT.value())
                                .build()))
                .switchIfEmpty(Mono.error(new StudentNotFoundException(id)));
    }

    public Mono<BaseResponse<List<StudentResponse>>> getAllStudents() {
        return studentRepository.findAll()
                .map(StudentResponse::toResponse)
                .collectList()
                .map(list -> BaseResponse.<List<StudentResponse>>builder()
                        .success(true)
                        .message("Students fetched successfully")
                        .data(list)
                        .timestamp(Instant.now())
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }

    public Mono<BaseResponse<PaginatedResponse<StudentResponse>>> getAllStudents(int page, int size, String sortBy, String direction) {
        String order = "desc".equalsIgnoreCase(direction) ? "DESC" : "ASC";
        int offset = page * size;
        String query = String.format("select * from student order by %s %s limit %d offset %d", sortBy, order, size, offset);
        Mono<List<StudentResponse>> studentMono = databaseClient.sql(query)
                .map(row -> Student.builder()
                        .id(row.get("id", Long.class))
                        .name(row.get("name", String.class))
                        .age(row.get("age", Integer.class))
                        .build())
                .all()
                .map(StudentResponse::toResponse)
                .collectList();
        Mono<Long> countMono = databaseClient.sql("select count(*) as total from student")
                .map(row -> row.get("total", Long.class))
                .one();
        return Mono.zip(studentMono, countMono)
                .map(tuple -> {
                    List<StudentResponse> students = tuple.getT1();
                    long totalItems = tuple.getT2();
                    int totalPages = (int) Math.ceil((double) totalItems / size);

                    PaginatedResponse<StudentResponse> paginated = PaginatedResponse.<StudentResponse>builder()
                            .items(students)
                            .totalItems(totalItems)
                            .currentPage(page)
                            .pageSize(size)
                            .totalPages(totalPages)
                            .build();

                    return BaseResponse.<PaginatedResponse<StudentResponse>>builder()
                            .success(true)
                            .message("Students fetched successfully")
                            .data(paginated)
                            .timestamp(Instant.now())
                            .statusCode(HttpStatus.OK.value())
                            .build();
                });

    }
}

package com.roze.repository;

import com.roze.entity.Author;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer>, JpaSpecificationExecutor<Author> {
    @Transactional
    List<Author> findByNamedQuery(@Param("age") int age);

    @Modifying
    @Transactional
    void updateByNamedQuery(@Param("age") int age);

    @Query("update Author a set a.age=:age where a.id=:id")
    @Modifying
    @Transactional
    int updateAuthorAgeById(int age, int id);

    @Modifying
    @Transactional
    @Query("update  Author  a set a.age=:age")
    void updateAllAuthorsAge(int age);

    //retrieve list of authors by using last name
    //or,
    // select * from author where last_name='firoze';
    List<Author> findAllByLastName(String lastName);

    //retrieve list of authors by using last name with ignore case
    List<Author> findAllByLastNameIgnoreCase(String lastName);

    //retrieve list of authors by using last name with ignore case and containing like '%fi%'
    List<Author> findAllByLastNameContainingIgnoreCase(String lastName);

    //retrieve list of authors by using last name starts with like 'fi%' with ignore case
    List<Author> findAllByLastNameStartingWithIgnoreCase(String lastName);

    //retrieve list of authors by using last name ends with like '%fi' with ignore case
    List<Author> findAllByLastNameEndsWithIgnoreCase(String lastName);

    //retrieve list of authors by using last name in  'fi, an, ro' with ignore case
    List<Author> findAllByLastNameInIgnoreCase(List<String> lastName);
}

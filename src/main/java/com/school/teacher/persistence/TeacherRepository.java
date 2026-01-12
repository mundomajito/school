package com.school.teacher.persistence;

import com.school.teacher.domain.Teacher;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @EntityGraph(attributePaths = "subjects")
    List<Teacher> findAll();

    @EntityGraph(attributePaths = "subjects")
    Optional<Teacher> findById(Long id);

    Optional<Teacher> findByEmail(String email);

    boolean existsByEmail(String email);
}

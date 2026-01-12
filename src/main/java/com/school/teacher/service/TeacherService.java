package com.school.teacher.service;

import com.school.common.exception.ConflictException;
import com.school.common.exception.ResourceNotFoundException;
import com.school.teacher.api.dto.SubjectSummary;
import com.school.teacher.api.dto.TeacherCreateRequest;
import com.school.teacher.api.dto.TeacherResponse;
import com.school.teacher.api.dto.TeacherUpdateRequest;
import com.school.teacher.domain.Teacher;
import com.school.teacher.persistence.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class TeacherService {

    private final TeacherRepository repo;

    public TeacherService(TeacherRepository repo) {
        this.repo = repo;
    }

    public TeacherResponse create(TeacherCreateRequest req) {
        if (repo.existsByEmail(req.email())) {
            throw new ConflictException("Email already exists: " + req.email());
        }

        Teacher teacher = new Teacher(
                req.firstName(),
                req.lastName(),
                req.email(),
                req.hireDate(),
                req.salary()
        );

        Teacher saved = repo.save(teacher);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<TeacherResponse> findAll() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public TeacherResponse findById(Long id) {
        Teacher teacher = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found: " + id));
        return toResponse(teacher);
    }

    public TeacherResponse updatePartial(Long id, TeacherUpdateRequest req) {
        Teacher teacher = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found: " + id));

        // Email unique check (solo si viene email y es distinto)
        if (req.email() != null && !req.email().equals(teacher.getEmail())) {
            if (repo.existsByEmail(req.email())) {
                throw new ConflictException("Email already exists: " + req.email());
            }
            teacher.setEmail(req.email());
        }

        if (req.firstName() != null) teacher.setFirstName(req.firstName());
        if (req.lastName() != null) teacher.setLastName(req.lastName());
        if (req.hireDate() != null) teacher.setHireDate(req.hireDate());
        if (req.salary() != null) teacher.setSalary(req.salary());
        if (req.active() != null) teacher.setActive(req.active());

        Teacher saved = repo.save(teacher);
        return toResponse(saved);
    }

    public void deleteById(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Teacher not found: " + id);
        }
        repo.deleteById(id);
    }

    private TeacherResponse toResponse(Teacher t) {
        var subjects = t.getSubjects().stream()
                .map(s -> new SubjectSummary(s.getId(), s.getName()))
                .sorted(Comparator.comparing(SubjectSummary::name))
                .toList();
        return new TeacherResponse(
                t.getId(),
                t.getFirstName(),
                t.getLastName(),
                t.getEmail(),
                t.getHireDate(),
                t.getSalary(),
                t.isActive(),
                subjects
        );
    }
}

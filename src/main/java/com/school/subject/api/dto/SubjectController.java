package com.school.subject.api;

import com.school.subject.domain.Subject;
import com.school.subject.service.SubjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    private final SubjectService service;

    public SubjectController(SubjectService service) {
        this.service = service;
    }

    // GET http://localhost:8080/api/subjects
    @GetMapping
    public List<Subject> getAll() {
        return service.findAll();
    }

    // POST http://localhost:8080/api/subjects
    @PostMapping
    public Subject create(@RequestBody Subject subject) {
        return service.create(subject.getName());
    }
    @GetMapping("/{id}")
    public Subject getById(@PathVariable Long id) {
        return service.findById(id);
    }

    // DELETE http://localhost:8080/api/subjects/1
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}

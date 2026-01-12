package com.school.teacher.api;

import com.school.teacher.api.dto.TeacherCreateRequest;
import com.school.teacher.api.dto.TeacherResponse;
import com.school.teacher.api.dto.TeacherUpdateRequest;
import com.school.teacher.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherResponse create(@Valid @RequestBody TeacherCreateRequest req) {
        return service.create(req);
    }

    @GetMapping
    public List<TeacherResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public TeacherResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PatchMapping("/{id}")
    public TeacherResponse updatePartial(@PathVariable Long id,
                                         @Valid @RequestBody TeacherUpdateRequest req) {
        return service.updatePartial(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}

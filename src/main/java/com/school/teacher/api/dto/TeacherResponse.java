package com.school.teacher.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record TeacherResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        LocalDate hireDate,
        BigDecimal salary,
        boolean active,
        List<SubjectSummary> subjects
) {
}

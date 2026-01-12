package com.school.teacher.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * PATCH: todos los campos opcionales.
 * Si vienen null -> no se actualizan.
 */
public record TeacherUpdateRequest(
        @Size(max = 100) String firstName,
        @Size(max = 100) String lastName,
        @Email @Size(max = 150) String email,
        LocalDate hireDate,
        @PositiveOrZero BigDecimal salary,
        Boolean active
) {}

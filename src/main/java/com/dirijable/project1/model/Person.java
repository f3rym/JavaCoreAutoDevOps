package com.dirijable.project1.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private Integer id;

    @NotEmpty(message = "name shouldn`t be empty!")
    @Pattern(regexp = "[A-Z][a-z]+ [A-Z][a-z]+ [A-Z][a-z]+", message = "name should be: Firstname Lastname Surname")
    private String name;

    @Min(value = 1905, message = "birth year should be greater than 1905")
    @Max(value = 2025, message = "birth year should be lower than 2025")
    private Integer birthyear;
}

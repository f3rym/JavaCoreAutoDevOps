package com.dirijable.project1.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Book {

    private Integer id;
    private Integer personId;

    @NotEmpty(message = "title shouldn`t be empty")
    private String title;

    @NotEmpty(message = "author shouldn`t be empty")
    private String author;

    @Min(value = 0, message = "year should be greater than 0")
    private Integer publishYear;
}

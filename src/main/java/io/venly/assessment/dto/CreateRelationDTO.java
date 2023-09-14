package io.venly.assessment.dto;

import jakarta.validation.constraints.Pattern;

public record CreateRelationDTO(
        @Pattern(regexp = "^[a-zA-Z ]+$", message = "Only lowercase letters, uppercase letters, and spaces are allowed")
        String word1,
        @Pattern(regexp = "^[a-zA-Z ]+$", message = "Only lowercase letters, uppercase letters, and spaces are allowed")
        String relation,
        @Pattern(regexp = "^[a-zA-Z ]+$", message = "Only lowercase letters, uppercase letters, and spaces are allowed")
        String word2
) { }

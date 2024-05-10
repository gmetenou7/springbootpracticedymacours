package com.dyma.springpracticecours;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record Player(
        @NotBlank(message = "le nom est obligatoire") String firstName,
        @NotBlank String lastName,
        @NotNull(message = "la date de naissance ne doit pas être null") @PastOrPresent(message = "present ou passé") LocalDate birthDate,
        @Valid Rank rank
) {


}

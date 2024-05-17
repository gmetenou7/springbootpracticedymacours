package com.dyma.springpracticecours.errors;

import java.time.LocalDate;

public record Error(
        int statusCode,
        String message
) {
}

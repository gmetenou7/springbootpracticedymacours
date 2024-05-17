package com.dyma.springpracticecours;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record Rank(
      @Positive(message = "la position ne doit pas être un nombre négatif et commence par 1") int position,
      @PositiveOrZero(message = "Points must be more than zero") int points
) {
}

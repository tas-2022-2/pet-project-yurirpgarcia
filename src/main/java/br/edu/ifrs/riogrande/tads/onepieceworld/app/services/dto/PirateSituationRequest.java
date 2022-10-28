package br.edu.ifrs.riogrande.tads.onepieceworld.app.services.dto;

import javax.validation.constraints.NotNull;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PirateSituationRequest {

  @NotNull(message = "Pirate situation is required")
  String isCurrently;

}

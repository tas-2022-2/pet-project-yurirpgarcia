package br.edu.ifrs.riogrande.tads.onepieceworld.app.services.dto;

import javax.validation.constraints.NotNull;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PirateRequest {

  @NotNull(message = "Pirate name is required")
  String name;

  String aka;

  @NotNull(message = "Crew id is required")
  String crewId;

  @NotNull(message = "Crew position id is required")
  String crewPositionId;

}

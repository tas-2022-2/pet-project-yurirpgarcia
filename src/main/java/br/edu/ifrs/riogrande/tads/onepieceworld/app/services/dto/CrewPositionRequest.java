package br.edu.ifrs.riogrande.tads.onepieceworld.app.services.dto;

import javax.validation.constraints.NotNull;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CrewPositionRequest {

  @NotNull(message = "Crew position name is required")
  String name;

}

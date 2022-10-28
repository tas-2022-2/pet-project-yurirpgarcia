package br.edu.ifrs.riogrande.tads.onepieceworld.app.services.dto;

import lombok.*;
import java.util.*;
import lombok.experimental.FieldDefaults;
import br.edu.ifrs.riogrande.tads.onepieceworld.app.entity.Crew;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CrewWithoutPiratesResponse {

  UUID id;

  String name;

  public CrewWithoutPiratesResponse(Crew crew) {
    id = crew.getId();
    name = crew.getName();
  }

}

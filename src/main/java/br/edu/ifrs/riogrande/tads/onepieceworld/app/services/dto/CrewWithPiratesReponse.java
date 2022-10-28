package br.edu.ifrs.riogrande.tads.onepieceworld.app.services.dto;

import java.util.*;
import java.util.stream.Collectors;

import lombok.*;
import lombok.experimental.FieldDefaults;
import br.edu.ifrs.riogrande.tads.onepieceworld.app.entity.Crew;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CrewWithPiratesReponse extends CrewWithoutPiratesResponse {

  List<PirateWithCrewPositionResponse> pirates;

  public CrewWithPiratesReponse(Crew crew) {
    super(crew);

    pirates = crew
        .getPirates().stream()
        .map(p -> new PirateWithCrewPositionResponse(p))
        .collect(Collectors.toList());
  }

}

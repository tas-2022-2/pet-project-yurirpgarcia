package br.edu.ifrs.riogrande.tads.onepieceworld.app.services.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import br.edu.ifrs.riogrande.tads.onepieceworld.app.entity.Pirate;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PirateWithCrewPositionAndCrewResponse extends PirateWithCrewPositionResponse {

  CrewWithoutPiratesResponse crew;

  public PirateWithCrewPositionAndCrewResponse(Pirate pirate) {
    super(pirate);

    crew = new CrewWithoutPiratesResponse(pirate.getCrew());
  }

}

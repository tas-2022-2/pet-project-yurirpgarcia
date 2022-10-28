package br.edu.ifrs.riogrande.tads.onepieceworld.app.services.dto;

import java.util.List;
import java.util.stream.Collectors;

import lombok.*;
import lombok.experimental.FieldDefaults;

import br.edu.ifrs.riogrande.tads.onepieceworld.app.entity.Pirate;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PirateResponse extends PirateWithCrewPositionAndCrewResponse {

  List<BountyWithoutPirateResponse> bounties;

  public PirateResponse(Pirate pirate) {
    super(pirate);

    bounties = pirate.getBounties()
        .stream()
        .map(b -> new BountyWithoutPirateResponse(b))
        .collect(Collectors.toList());
  }
}

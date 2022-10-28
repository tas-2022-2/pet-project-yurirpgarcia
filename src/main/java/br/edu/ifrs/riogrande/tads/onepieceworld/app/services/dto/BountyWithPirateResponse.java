package br.edu.ifrs.riogrande.tads.onepieceworld.app.services.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import br.edu.ifrs.riogrande.tads.onepieceworld.app.entity.Bounty;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BountyWithPirateResponse extends BountyWithoutPirateResponse {

  PirateWithCrewPositionAndCrewResponse pirate;

  public BountyWithPirateResponse(Bounty bounty) {
    super(bounty);

    pirate = new PirateWithCrewPositionAndCrewResponse(bounty.getPirate());
  }

  

}
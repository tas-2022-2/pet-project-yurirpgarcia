package br.edu.ifrs.riogrande.tads.onepieceworld.app.services.dto;

import java.util.UUID;
import lombok.*;
import lombok.experimental.FieldDefaults;
import br.edu.ifrs.riogrande.tads.onepieceworld.app.entity.*;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BountyWithoutPirateResponse {

  UUID id;
  Belly value;
  String reasonDescription;

  public BountyWithoutPirateResponse(Bounty bounty) {
    id = bounty.getId();
    value = bounty.getValue();
    reasonDescription = bounty.getReasonDescription();
  }

}

package br.edu.ifrs.riogrande.tads.onepieceworld.app.services.dto;

import br.edu.ifrs.riogrande.tads.onepieceworld.app.entity.Pirate;

import java.util.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import br.edu.ifrs.riogrande.tads.onepieceworld.app.entity.*;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PirateWithCrewPositionResponse {

  UUID id;
  String name;
  String aka;
  CrewPosition crewPosition;
  PirateIsCurrently isCurrently;

  public PirateWithCrewPositionResponse(Pirate pirate) {
    id = pirate.getId();
    name = pirate.getName();
    aka = pirate.getAka();
    crewPosition = pirate.getCrewPosition();
    isCurrently = pirate.getIsCurrently();
  }
  
  
}

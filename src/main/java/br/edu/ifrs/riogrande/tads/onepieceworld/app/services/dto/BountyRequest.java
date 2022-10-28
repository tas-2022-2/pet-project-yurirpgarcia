package br.edu.ifrs.riogrande.tads.onepieceworld.app.services.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BountyRequest {

  @NotNull(message = "Bounty value is required")
  BigDecimal value;

  String reasonDescription;

}

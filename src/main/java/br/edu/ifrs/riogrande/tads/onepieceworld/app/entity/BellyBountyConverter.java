package br.edu.ifrs.riogrande.tads.onepieceworld.app.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Converter
public class BellyBountyConverter implements AttributeConverter<Belly, BigDecimal> {

  @Override
  public BigDecimal convertToDatabaseColumn(Belly belly) {
    return belly.getValue();
  }

  @Override
  public Belly convertToEntityAttribute(BigDecimal value) {
    return new Belly(value);
  }

}

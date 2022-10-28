package br.edu.ifrs.riogrande.tads.onepieceworld.app.entity;

import lombok.Getter;
import java.math.BigDecimal;

@Getter
public class Belly implements Comparable<Belly> {

  private BigDecimal value;

  public Belly(BigDecimal value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return String.format("฿ %s", this.value.toPlainString());
  }

  @Override
  public int compareTo(Belly belly) {
    return value.compareTo(belly.getValue());
  }

}

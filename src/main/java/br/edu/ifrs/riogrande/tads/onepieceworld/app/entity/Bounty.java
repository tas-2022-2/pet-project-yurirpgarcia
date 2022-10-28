package br.edu.ifrs.riogrande.tads.onepieceworld.app.entity;

import lombok.*;
import java.util.UUID;
import javax.persistence.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "bounty")
public class Bounty {

  @Id
  @GeneratedValue
  private UUID id;

  @Column(name = "value", nullable = false)
  @Convert(converter = BellyBountyConverter.class)
  private Belly value;

  @Column(name = "reason_description")
  private String reasonDescription;

  @ManyToOne(optional = false)
  private Pirate pirate;

}

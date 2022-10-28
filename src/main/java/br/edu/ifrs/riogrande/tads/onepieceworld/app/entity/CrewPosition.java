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
@Table(name = "crew_position")
public class CrewPosition {

  @Id
  @GeneratedValue
  UUID id;

  @Column(name = "name", nullable = false)
  String name;

}

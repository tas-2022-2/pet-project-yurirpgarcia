package br.edu.ifrs.riogrande.tads.onepieceworld.app.entity;

import lombok.*;
import java.util.*;
import javax.persistence.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "crew")
public class Crew {

  @Id
  @GeneratedValue
  UUID id;

  @Column(name = "name", nullable = false)
  String name;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "crew", cascade = CascadeType.ALL)
  List<Pirate> pirates;

}

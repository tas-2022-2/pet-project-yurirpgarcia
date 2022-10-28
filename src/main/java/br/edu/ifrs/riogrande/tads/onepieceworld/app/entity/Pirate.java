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
@Table(name = "pirate")
public class Pirate {

  @Id
  @GeneratedValue
  UUID id;

  @Column(name = "name", nullable = false)
  String name;

  @Column(name = "aka")
  String aka;

  @ManyToOne(optional = false)
  Crew crew;

  @ManyToOne(optional = false, cascade = CascadeType.ALL)
  CrewPosition crewPosition;

  @Enumerated(EnumType.STRING)
  @Column(name = "is_currently", nullable = false, columnDefinition = "enum")
  PirateIsCurrently isCurrently;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "pirate", cascade = CascadeType.ALL)
  @OrderBy("value DESC")
  List<Bounty> bounties;

}

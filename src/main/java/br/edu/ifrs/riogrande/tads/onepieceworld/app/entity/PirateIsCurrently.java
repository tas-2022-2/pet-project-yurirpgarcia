package br.edu.ifrs.riogrande.tads.onepieceworld.app.entity;

public enum PirateIsCurrently implements PirateSituationState {
  FREE() {
    @Override
    public void ifCantChangeToSituationThrow(PirateIsCurrently situation) {
      if (situation == PirateIsCurrently.FREE) {
        throw new IllegalArgumentException("Pirate is already free.");
      }
    }
  },
  IN_JAIL() {
    @Override
    public void ifCantChangeToSituationThrow(PirateIsCurrently situation) {
      if (situation == PirateIsCurrently.IN_JAIL) {
        throw new IllegalArgumentException("Pirate is already in jail.");
      }
    }
  },
  DECEASED() {
    @Override
    public void ifCantChangeToSituationThrow(PirateIsCurrently situation) {
      if (situation == PirateIsCurrently.DECEASED) {
        throw new IllegalArgumentException("Pirate is already dead.");
      }

      throw new IllegalArgumentException("Pirate is not between us anymore.");
    } 
  };

}
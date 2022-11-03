package br.edu.ifrs.riogrande.tads.onepieceworld.app.services;

import java.util.Optional;
import java.util.UUID;
import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import br.edu.ifrs.riogrande.tads.onepieceworld.app.entity.Bounty;
import br.edu.ifrs.riogrande.tads.onepieceworld.app.entity.Pirate;
import br.edu.ifrs.riogrande.tads.onepieceworld.app.exceptions.NotFoundException;
import br.edu.ifrs.riogrande.tads.onepieceworld.app.repository.BountyRepository;
import br.edu.ifrs.riogrande.tads.onepieceworld.app.repository.PirateRepository;
import br.edu.ifrs.riogrande.tads.onepieceworld.app.services.dto.BountyRequest;

public class BountyServiceTest {

  BountyService bountyService;
  BountyRepository bountyRepositoryStub;
  PirateRepository pirateRepositoryStub;

  @BeforeEach
  void init() {
    bountyRepositoryStub = new BountyRepositoryStub();
    pirateRepositoryStub = new PirateRepositoryStub();
    bountyService = new BountyService(bountyRepositoryStub, pirateRepositoryStub);
  }

  @Test
  void testBountyPirateNotFound() {
    var pirateId = UUID.fromString("a0540e0d-a0fb-4f10-afe0-74743c9f2641");

    var bountyRequest = new BountyRequest();

    Assertions
        .assertThatThrownBy(() -> bountyService.create(pirateId, bountyRequest))
        .isInstanceOf(NotFoundException.class)
        .hasMessage("Pirate not found");
  }

  @Test
  void testBountyValueMustBeGreaterThan0() {
    var pirateId = UUID.fromString("9f2af331-8b66-486d-b699-54429c74622d");

    var bountyRequest = new BountyRequest();
    bountyRequest.setValue(BigDecimal.valueOf(0));

    Assertions
        .assertThatThrownBy(() -> bountyService.create(pirateId, bountyRequest))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Bounty value must be greater than 0");
  }

  @Test
  void testBountyReasonMustHaveAtLeast10CharsWhenInformed() {
    var pirateId = UUID.fromString("9f2af331-8b66-486d-b699-54429c74622d");

    var bountyRequest = new BountyRequest();
    bountyRequest.setValue(BigDecimal.valueOf(10000000));
    bountyRequest.setReasonDescription("too short");

    Assertions
        .assertThatThrownBy(() -> bountyService.create(pirateId, bountyRequest))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("If bounty reason was informed then it must be at least 10 characters long");
  }

  @Test
  void testSaveBountySuccess() {
    var pirateId = UUID.fromString("9f2af331-8b66-486d-b699-54429c74622d");

    var bountyRequest = new BountyRequest();
    bountyRequest.setValue(BigDecimal.valueOf(10000000));
    bountyRequest.setReasonDescription("A long enough bounty reason");

    var bounty = bountyService.create(pirateId, bountyRequest);

    Assertions
        .assertThat(bounty)
        .isInstanceOf(Bounty.class)
        .hasFieldOrPropertyWithValue("reasonDescription", "A long enough bounty reason");

    Assertions
        .assertThat(bounty.getPirate())
        .isInstanceOf(Pirate.class)
        .hasFieldOrPropertyWithValue("name", "Pirate test");
  }

  @Test
  void testBountyNotFound() {
    var id = UUID.fromString("465f18cc-bf22-4868-a946-4e91c92b59dd");

    Assertions
        .assertThatThrownBy(() -> bountyService.delete(id))
        .isInstanceOf(NotFoundException.class)
        .hasMessage("Bounty not found");
  }

  static class PirateRepositoryStub implements PirateRepository {
    @Override
    public Pirate save(Pirate p) {
      return p;
    }

    @Override
    public List<Pirate> findAll() {
      return null;
    }

    @Override
    public Optional<Pirate> findById(UUID id) {
      var idToTest = UUID.fromString("9f2af331-8b66-486d-b699-54429c74622d");

      if (id.compareTo(idToTest) == 0) {
        var pirate = new Pirate();
        pirate.setId(idToTest);
        pirate.setName("Pirate test");

        return Optional.of(pirate);
      }

      return Optional.empty();
    }

    @Override
    public void delete(Pirate p) {
    }
  }

  static class BountyRepositoryStub implements BountyRepository {

    @Override
    public void delete(Bounty b) {
    }

    @Override
    public List<Bounty> findAll() {
      return null;
    }

    @Override
    public Optional<Bounty> findById(UUID id) {
      return Optional.empty();
    }

    @Override
    public Bounty save(Bounty b) {
      return b;
    }

  }

}

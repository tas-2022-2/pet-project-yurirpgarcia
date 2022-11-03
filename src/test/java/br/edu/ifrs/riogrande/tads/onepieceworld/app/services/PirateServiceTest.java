package br.edu.ifrs.riogrande.tads.onepieceworld.app.services;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import br.edu.ifrs.riogrande.tads.onepieceworld.app.entity.*;
import br.edu.ifrs.riogrande.tads.onepieceworld.app.services.dto.*;
import br.edu.ifrs.riogrande.tads.onepieceworld.app.exceptions.*;
import br.edu.ifrs.riogrande.tads.onepieceworld.app.repository.*;

public class PirateServiceTest {

  PirateService pirateService;

  PirateRepository pirateRepositoryStub;
  CrewRepository crewRepositoryStub;
  CrewPositionRepository crewPositionRepositoryStub;

  @BeforeEach
  void init() {
    pirateRepositoryStub = new PirateRepositoryStub();
    crewRepositoryStub = new CrewRepositoryStub();
    crewPositionRepositoryStub = new CrewPositionRepositoryStub();

    pirateService = new PirateService(pirateRepositoryStub, crewRepositoryStub, crewPositionRepositoryStub);
  }

  @Test
  void testPirateCrewNotFoudThrowsException() {
    var pirateRequest = new PirateRequest();

    pirateRequest.setCrewId("1d60c66d-d62b-4e67-9fff-1f746430baa5");
    pirateRequest.setCrewPositionId("7e55880e-c475-4eb3-8489-ac918981e1bb");

    Assertions
        .assertThatThrownBy(() -> pirateService.create(pirateRequest))
        .isInstanceOf(NotFoundException.class)
        .hasMessage("Crew not found");
  }

  @Test
  void testPirateCrewPositionNotFoudThrowsException() {
    var pirateRequest = new PirateRequest();

    pirateRequest.setCrewId("c59de320-551e-4810-8f4f-e1877c9717a8");
    pirateRequest.setCrewPositionId("7e55880e-c475-4eb3-8489-ac918981e1bb");

    Assertions
        .assertThatThrownBy(() -> pirateService.create(pirateRequest))
        .isInstanceOf(NotFoundException.class)
        .hasMessage("Crew position not found");
  }

  @Test
  void testSavePirateSuccess() {
    var pirateRequest = new PirateRequest();

    pirateRequest.setName("Pirate test");
    pirateRequest.setCrewId("c59de320-551e-4810-8f4f-e1877c9717a8");
    pirateRequest.setCrewPositionId("6dfc9eff-c579-4d81-b9ba-188466bbaf7a");

    var pirate = pirateService.create(pirateRequest);

    Assertions
        .assertThat(pirate)
        .isInstanceOf(Pirate.class)
        .hasFieldOrPropertyWithValue("name", "Pirate test")
        .hasFieldOrPropertyWithValue("isCurrently", PirateIsCurrently.FREE);

    Assertions
        .assertThat(pirate.getCrew())
        .isInstanceOf(Crew.class)
        .hasFieldOrPropertyWithValue("name", "Crew test");

    Assertions
        .assertThat(pirate.getCrewPosition())
        .isInstanceOf(CrewPosition.class)
        .hasFieldOrPropertyWithValue("name", "Crew position test");
  }

  @Test
  void testPirateNotFound() {
    var id = UUID.fromString("465f18cc-bf22-4868-a946-4e91c92b59dd");

    Assertions
        .assertThatThrownBy(() -> pirateService.delete(id))
        .isInstanceOf(NotFoundException.class)
        .hasMessage("Pirate not found");
  }

  @Test
  void testInvalidPirateStatusThrowsException() {
    var id = UUID.fromString("9f2af331-8b66-486d-b699-54429c74622d");

    var pirateSituationRequest = new PirateSituationRequest();
    pirateSituationRequest.setIsCurrently("DEAD");

    Assertions
        .assertThatThrownBy(() -> pirateService.updateSituation(id, pirateSituationRequest))
        .isInstanceOf(ServiceException.class)
        .hasMessageContaining("DEAD is not a valid situation");
  }

  @Test
  void testPirateCantChangeToSameStatus() {
    var id = UUID.fromString("9f2af331-8b66-486d-b699-54429c74622d");

    var pirateSituationRequest = new PirateSituationRequest();
    pirateSituationRequest.setIsCurrently("DECEASED");

    Assertions
        .assertThatThrownBy(() -> pirateService.updateSituation(id, pirateSituationRequest))
        .isInstanceOf(ServiceException.class)
        .hasMessageContaining("Pirate is already");
  }

  @Test
  void testPirateIsDeadSoItCantChangeStatus() {
    var id = UUID.fromString("9f2af331-8b66-486d-b699-54429c74622d");

    var pirateSituationRequest = new PirateSituationRequest();
    pirateSituationRequest.setIsCurrently("IN_JAIL");

    Assertions
        .assertThatThrownBy(() -> pirateService.updateSituation(id, pirateSituationRequest))
        .isInstanceOf(ServiceException.class)
        .hasMessage("Pirate is not between us anymore.");
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
        pirate.setIsCurrently(PirateIsCurrently.DECEASED);

        return Optional.of(pirate);
      }

      return Optional.empty();
    }

    @Override
    public void delete(Pirate p) {
    }
  }

  static class CrewRepositoryStub implements CrewRepository {

    @Override
    public void delete(Crew c) {
    }

    @Override
    public List<Crew> findAll() {
      return null;
    }

    @Override
    public Optional<Crew> findById(UUID id) {
      var idToTest = UUID.fromString("c59de320-551e-4810-8f4f-e1877c9717a8");

      if (id.compareTo(idToTest) == 0) {
        var crew = new Crew();
        crew.setId(idToTest);
        crew.setName("Crew test");

        return Optional.of(crew);
      }

      return Optional.empty();
    }

    @Override
    public Crew save(Crew c) {
      return null;
    }
  }

  static class CrewPositionRepositoryStub implements CrewPositionRepository {

    @Override
    public void delete(CrewPosition cp) {
    }

    @Override
    public List<CrewPosition> findAll() {
      return null;
    }

    @Override
    public Optional<CrewPosition> findById(UUID id) {
      var idToTest = UUID.fromString("6dfc9eff-c579-4d81-b9ba-188466bbaf7a");

      if (id.compareTo(idToTest) == 0) {
        var crewPosition = new CrewPosition();
        crewPosition.setId(idToTest);
        crewPosition.setName("Crew position test");

        return Optional.of(crewPosition);
      }

      return Optional.empty();
    }

    @Override
    public CrewPosition save(CrewPosition cp) {
      return null;
    }
  }
}

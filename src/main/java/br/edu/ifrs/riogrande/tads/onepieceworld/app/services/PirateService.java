package br.edu.ifrs.riogrande.tads.onepieceworld.app.services;

import java.util.*;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import br.edu.ifrs.riogrande.tads.onepieceworld.app.entity.*;
import br.edu.ifrs.riogrande.tads.onepieceworld.app.repository.*;
import br.edu.ifrs.riogrande.tads.onepieceworld.app.exceptions.*;
import br.edu.ifrs.riogrande.tads.onepieceworld.app.services.dto.*;

@Service
@RequiredArgsConstructor
public class PirateService {

  private final PirateRepository pirateRepository;
  private final CrewRepository crewRepository;
  private final CrewPositionRepository crewPositionRepository;

  public Pirate create(@Valid PirateRequest request) {
    Crew crew = findCrewByIdAndThrowIfNotFound(request.getCrewId());
    CrewPosition crewPosition = findCrewPositionByIdAndThrowIfNotFound(request.getCrewPositionId());

    Pirate pirate = new Pirate();
    pirate.setCrew(crew);
    pirate.setAka(request.getAka());
    pirate.setName(request.getName());
    pirate.setCrewPosition(crewPosition);
    pirate.setIsCurrently(PirateIsCurrently.FREE);

    return pirateRepository.save(pirate);
  }

  public List<PirateWithCrewPositionAndCrewResponse> list() {
    return pirateRepository.findAll()
        .stream()
        .map(p -> new PirateWithCrewPositionAndCrewResponse(p))
        .collect(Collectors.toList());
  }

  public void delete(UUID id) {
    Pirate pirate = findPirateByIdAndThrowIfNotFound(id);
    pirateRepository.delete(pirate);
  }

  public void update(UUID id, @Valid PirateRequest request) {
    Crew crew = findCrewByIdAndThrowIfNotFound(request.getCrewId());
    CrewPosition crewPosition = findCrewPositionByIdAndThrowIfNotFound(request.getCrewPositionId());

    Pirate pirate = findPirateByIdAndThrowIfNotFound(id);
    pirate.setCrew(crew);
    pirate.setAka(request.getAka());
    pirate.setName(request.getName());
    pirate.setCrewPosition(crewPosition);

    pirateRepository.save(pirate);
  }

  public PirateResponse load(UUID id) {
    return new PirateResponse(findPirateByIdAndThrowIfNotFound(id));
  }

  public Pirate updateSituation(UUID id, PirateSituationRequest request) {
    PirateIsCurrently possiblyNewSituation = null;

    try {
      possiblyNewSituation = PirateIsCurrently.valueOf(request.getIsCurrently());
    } catch (IllegalArgumentException e) {
      throw new ServiceException(String.format("%s is not a valid situation", request.getIsCurrently()));
    }

    Pirate pirate = findPirateByIdAndThrowIfNotFound(id);

    try {
      pirate
          .getIsCurrently()
          .ifCantChangeToSituationThrow(possiblyNewSituation);

      pirate.setIsCurrently(possiblyNewSituation);
    } catch (IllegalArgumentException e) {
      throw new ServiceException(e.getMessage());
    }

    return pirateRepository.save(pirate);
  }

  private Pirate findPirateByIdAndThrowIfNotFound(UUID id) {
    return pirateRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Pirate not found"));
  }

  private Crew findCrewByIdAndThrowIfNotFound(String id) {
    return crewRepository
        .findById(UUID.fromString(id))
        .orElseThrow(() -> new NotFoundException("Crew not found"));
  }

  private CrewPosition findCrewPositionByIdAndThrowIfNotFound(String id) {
    return crewPositionRepository
        .findById(UUID.fromString(id))
        .orElseThrow(() -> new NotFoundException("Crew position not found"));
  }

}

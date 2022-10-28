package br.edu.ifrs.riogrande.tads.onepieceworld.app.services;

import lombok.RequiredArgsConstructor;

import java.util.*;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import br.edu.ifrs.riogrande.tads.onepieceworld.app.entity.CrewPosition;
import br.edu.ifrs.riogrande.tads.onepieceworld.app.exceptions.NotFoundException;
import br.edu.ifrs.riogrande.tads.onepieceworld.app.repository.CrewPositionRepository;
import br.edu.ifrs.riogrande.tads.onepieceworld.app.services.dto.CrewPositionRequest;

@Service
@RequiredArgsConstructor
public class CrewPositionService {

  private final CrewPositionRepository crewPositionRepository;

  public CrewPosition create(@Valid CrewPositionRequest request) {
    CrewPosition crewPosition = new CrewPosition();
    crewPosition.setName(request.getName());

    return crewPositionRepository.save(crewPosition);
  }

  public List<CrewPosition> list() {
    return crewPositionRepository.findAll();
  }

  public void delete(UUID id) {
    CrewPosition crew = findCrewPositionByIdAndThrowIfNotFound(id);
    crewPositionRepository.delete(crew);
  }

  public void update(UUID id, @Valid CrewPositionRequest request) {
    CrewPosition crew = findCrewPositionByIdAndThrowIfNotFound(id);
    crew.setName(request.getName());

    crewPositionRepository.save(crew);
  }

  public CrewPosition load(UUID id) {
    return findCrewPositionByIdAndThrowIfNotFound(id);
  }

  private CrewPosition findCrewPositionByIdAndThrowIfNotFound(UUID id) {
    return crewPositionRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Crew position not found"));
  }

}

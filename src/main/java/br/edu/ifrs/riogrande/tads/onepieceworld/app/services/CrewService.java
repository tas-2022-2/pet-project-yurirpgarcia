package br.edu.ifrs.riogrande.tads.onepieceworld.app.services;

import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import br.edu.ifrs.riogrande.tads.onepieceworld.app.entity.Crew;
import br.edu.ifrs.riogrande.tads.onepieceworld.app.services.dto.*;
import br.edu.ifrs.riogrande.tads.onepieceworld.app.repository.CrewRepository;
import br.edu.ifrs.riogrande.tads.onepieceworld.app.exceptions.NotFoundException;

@Service
@RequiredArgsConstructor
public class CrewService {

  private final CrewRepository crewRepository;

  public Crew create(@Valid CrewRequest request) {
    Crew crew = new Crew();
    crew.setName(request.getName());

    return crewRepository.save(crew);
  }

  public List<CrewWithoutPiratesResponse> list() {
    return crewRepository
        .findAll()
        .stream()
        .map(c -> new CrewWithoutPiratesResponse(c))
        .collect(Collectors.toList());
  }

  public void delete(UUID id) {
    Crew crew = findCrewByIdAndThrowIfNotFound(id);
    crewRepository.delete(crew);
  }

  public void update(UUID id, @Valid CrewRequest request) {
    Crew crew = findCrewByIdAndThrowIfNotFound(id);
    crew.setName(request.getName());

    crewRepository.save(crew);
  }

  public CrewWithPiratesReponse load(UUID id) {
    return new CrewWithPiratesReponse(findCrewByIdAndThrowIfNotFound(id));
  }

  private Crew findCrewByIdAndThrowIfNotFound(UUID id) {
    return crewRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Crew not found"));
  }

}

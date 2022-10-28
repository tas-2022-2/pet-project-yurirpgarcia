package br.edu.ifrs.riogrande.tads.onepieceworld.controller;

import java.net.URI;
import java.util.*;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.ifrs.riogrande.tads.onepieceworld.app.entity.CrewPosition;
import br.edu.ifrs.riogrande.tads.onepieceworld.app.services.CrewPositionService;
import br.edu.ifrs.riogrande.tads.onepieceworld.app.services.dto.CrewPositionRequest;
import lombok.*;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/crew-positions")
public class CrewPositionController {

  private final CrewPositionService crewPositionService;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> create(
      @RequestBody @Valid CrewPositionRequest body) {

    CrewPosition newCrewPosition = crewPositionService.create(body);

    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(newCrewPosition.getId())
        .toUri();

    return ResponseEntity.created(location).build();
  }

  @ResponseStatus(code = HttpStatus.OK)
  @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void update(
      @PathVariable(name = "id") String id,
      @RequestBody @Valid CrewPositionRequest body) {

    crewPositionService.update(UUID.fromString(id), body);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<?>> read() {
    return ResponseEntity.ok(crewPositionService.list());
  }

  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> readOne(
      @PathVariable(name = "id") String id) {

    CrewPosition crewPosition = crewPositionService.load(UUID.fromString(id));
    return ResponseEntity.ok(crewPosition);
  }

  @ResponseStatus(code = HttpStatus.OK)
  @DeleteMapping(path = "/{id}")
  public void delete(
      @PathVariable(name = "id") String id) {

    crewPositionService.delete(UUID.fromString(id));
  }

}

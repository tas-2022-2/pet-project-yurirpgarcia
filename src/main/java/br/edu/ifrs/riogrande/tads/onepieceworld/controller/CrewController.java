package br.edu.ifrs.riogrande.tads.onepieceworld.controller;

import java.util.*;
import java.net.URI;
import javax.validation.Valid;

import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.ifrs.riogrande.tads.onepieceworld.app.entity.Crew;
import br.edu.ifrs.riogrande.tads.onepieceworld.app.services.CrewService;
import br.edu.ifrs.riogrande.tads.onepieceworld.app.services.dto.*;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/crews")
public class CrewController {

  private final CrewService crewService;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> create(
      @RequestBody @Valid CrewRequest body) {

    Crew newCrew = crewService.create(body);

    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(newCrew.getId())
        .toUri();

    return ResponseEntity.created(location).build();
  }

  @ResponseStatus(code = HttpStatus.OK)
  @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void update(
      @PathVariable(name = "id") String id,
      @RequestBody @Valid CrewRequest body) {

    crewService.update(UUID.fromString(id), body);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<CrewWithoutPiratesResponse>> read() {
    return ResponseEntity.ok(crewService.list());
  }

  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CrewWithPiratesReponse> readOne(
      @PathVariable(name = "id") String id) {

    return ResponseEntity.ok(crewService.load(UUID.fromString(id)));
  }

  @ResponseStatus(code = HttpStatus.OK)
  @DeleteMapping(path = "/{id}")
  public void delete(
      @PathVariable(name = "id") String id) {

    crewService.delete(UUID.fromString(id));
  }

}

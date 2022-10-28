package br.edu.ifrs.riogrande.tads.onepieceworld.controller;

import java.net.URI;
import java.util.*;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.ifrs.riogrande.tads.onepieceworld.app.services.dto.*;
import br.edu.ifrs.riogrande.tads.onepieceworld.app.entity.Pirate;
import br.edu.ifrs.riogrande.tads.onepieceworld.app.services.PirateService;
import lombok.*;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/pirates")
public class PirateController {

  private final PirateService pirateService;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> create(
      @RequestBody @Valid PirateRequest body) {

    Pirate newPirate = pirateService.create(body);

    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(newPirate.getId())
        .toUri();

    return ResponseEntity.created(location).build();
  }

  @ResponseStatus(code = HttpStatus.OK)
  @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void update(
      @PathVariable(name = "id") String id,
      @RequestBody @Valid PirateRequest body) {

    pirateService.update(UUID.fromString(id), body);
  }

  @ResponseStatus(code = HttpStatus.OK)
  @PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void updateSituation(
      @PathVariable(name = "id") String id,
      @RequestBody @Valid PirateSituationRequest body) {

    pirateService.updateSituation(UUID.fromString(id), body);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<PirateWithCrewPositionAndCrewResponse>> read() {
    return ResponseEntity.ok(pirateService.list());
  }

  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PirateResponse> readOne(
      @PathVariable(name = "id") String id) {

    return ResponseEntity.ok(pirateService.load(UUID.fromString(id)));
  }

  @ResponseStatus(code = HttpStatus.OK)
  @DeleteMapping(path = "/{id}")
  public void delete(
      @PathVariable(name = "id") String id) {

    pirateService.delete(UUID.fromString(id));
  }

}

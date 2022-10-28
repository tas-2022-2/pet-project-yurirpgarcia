package br.edu.ifrs.riogrande.tads.onepieceworld.controller;

import lombok.*;
import java.net.URI;
import java.util.*;
import javax.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.ifrs.riogrande.tads.onepieceworld.app.entity.Bounty;
import br.edu.ifrs.riogrande.tads.onepieceworld.app.services.BountyService;
import br.edu.ifrs.riogrande.tads.onepieceworld.app.services.dto.BountyRequest;
import br.edu.ifrs.riogrande.tads.onepieceworld.app.services.dto.BountyWithPirateResponse;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class BountyController {

  private final BountyService bountyService;

  @PostMapping(path = "/pirates/{id}/bounties", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> create(
      @PathVariable(name = "id") String pirateId,
      @RequestBody @Valid BountyRequest body) {

    Bounty newBounty = bountyService.create(UUID.fromString(pirateId), body);

    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/bounties/{id}")
        .buildAndExpand(newBounty.getId())
        .toUri();

    return ResponseEntity.created(location).build();
  }

  @ResponseStatus(code = HttpStatus.OK)
  @PutMapping(path = "/bounties/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void update(
      @PathVariable(name = "id") String id,
      @RequestBody @Valid BountyRequest body) {

    bountyService.update(UUID.fromString(id), body);
  }

  @GetMapping(path = "/bounties/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BountyWithPirateResponse> read(
      @PathVariable(name = "id") String id) {

    return ResponseEntity.ok(bountyService.load(UUID.fromString(id)));
  }

  @ResponseStatus(code = HttpStatus.OK)
  @DeleteMapping(path = "/bounties/{id}")
  public void delete(
      @PathVariable(name = "id") String id) {

    bountyService.delete(UUID.fromString(id));
  }

}

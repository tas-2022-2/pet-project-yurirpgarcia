package br.edu.ifrs.riogrande.tads.onepieceworld.app.services;

import lombok.RequiredArgsConstructor;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import br.edu.ifrs.riogrande.tads.onepieceworld.app.entity.*;
import br.edu.ifrs.riogrande.tads.onepieceworld.app.exceptions.*;
import br.edu.ifrs.riogrande.tads.onepieceworld.app.repository.*;
import br.edu.ifrs.riogrande.tads.onepieceworld.app.services.dto.BountyRequest;
import br.edu.ifrs.riogrande.tads.onepieceworld.app.services.dto.BountyWithPirateResponse;

@Service
@RequiredArgsConstructor
public class BountyService {

  private final BountyRepository bountyRepository;
  private final PirateRepository pirateRepository;

  public Bounty create(UUID pirateId, @Valid BountyRequest request) {
    Pirate pirate = pirateRepository
        .findById(pirateId)
        .orElseThrow(() -> new NotFoundException("Pirate not found"));

    Bounty bounty = new Bounty();
    bounty.setPirate(pirate);
    bounty.setValue(new Belly(request.getValue()));
    bounty.setReasonDescription(request.getReasonDescription());

    validateBounty(bounty);

    return bountyRepository.save(bounty);
  }

  public void delete(UUID id) {
    Bounty bounty = findBountyByIdAndThrowIfNotFound(id);
    bountyRepository.delete(bounty);
  }

  public void update(UUID id, @Valid BountyRequest request) {
    Bounty bounty = findBountyByIdAndThrowIfNotFound(id);
    bounty.setValue(new Belly(request.getValue()));
    bounty.setReasonDescription(request.getReasonDescription());

    validateBounty(bounty);

    bountyRepository.save(bounty);
  }

  public BountyWithPirateResponse load(UUID id) {
    return new BountyWithPirateResponse(findBountyByIdAndThrowIfNotFound(id));
  }

  private Bounty findBountyByIdAndThrowIfNotFound(UUID id) {
    return bountyRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Bounty not found"));
  }

  private void validateBounty(Bounty bounty) {
    var zeroBelly = new Belly(0);

    if (bounty.getValue().compareTo(zeroBelly) != 1) {
      throw new IllegalArgumentException("Bounty value has to be greater than 0");
    }

    if (bounty.getReasonDescription() != null &&
        bounty.getReasonDescription().length() > 0 &&
        bounty.getReasonDescription().length() < 10) {

      throw new IllegalArgumentException("If bounty reason was informed then it must be at least 10 characters long");
    }
  }

}

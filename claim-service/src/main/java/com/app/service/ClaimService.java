package com.app.service;
import org.springframework.stereotype.Service;

import com.app.model.Claim;
import com.app.repository.ClaimRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class ClaimService {
  private final ClaimRepository repo;
  public ClaimService(ClaimRepository repo) { this.repo = repo; }

  public Claim createClaim(Claim c) {
    c.setStatus("SUBMITTED");
    c.setRaisedOn(LocalDate.now());
    return repo.save(c);
  }

  public List<Claim> getByPolicy(Long policyId) { return repo.findByPolicyId(policyId); }

  public List<Claim> getAll() { return repo.findAll(); }
}

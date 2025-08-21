package com.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.model.Claim;
import com.app.service.ClaimService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {
  private final ClaimService service;
  public ClaimController(ClaimService service) { this.service = service; }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody Claim c) {
    Claim saved = service.createClaim(c);
    return ResponseEntity.created(URI.create("/api/claims/" + saved.getId())).body(saved);
  }

  @GetMapping("/policy/{policyId}")
  public List<Claim> byPolicy(@PathVariable Long policyId) { return service.getByPolicy(policyId); }

  @GetMapping
  public List<Claim> all() { return service.getAll(); }
}

package com.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.model.Policy;
import com.app.service.PolicyService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {
  private final PolicyService service;
  public PolicyController(PolicyService service) { this.service = service; }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody Policy p) {
    try {
      Policy saved = service.createPolicy(p);
      return ResponseEntity.created(URI.create("/api/policies/" + saved.getId())).body(saved);
    } catch (IllegalArgumentException ex) {
      return ResponseEntity.badRequest().body(ex.getMessage());
    }
  }

  @GetMapping
  public List<Policy> all() { return service.getAllPolicies(); }

  @GetMapping("/user/{userId}")
  public List<Policy> byUser(@PathVariable Long userId) { return service.getByUser(userId); }
}


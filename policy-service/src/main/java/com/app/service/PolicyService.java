package com.app.service;



import org.springframework.stereotype.Service;

import com.app.client.UserClient;
import com.app.model.Policy;
import com.app.repository.PolicyRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

import java.util.List;

@Service
public class PolicyService {
  private final PolicyRepository repo;
  private final UserClient userClient;

  public PolicyService(PolicyRepository repo, UserClient userClient) {
    this.repo = repo;
    this.userClient = userClient;
  }

  public Policy createPolicy(Policy policy) {
    UserClient.UserDto user = validateUser(policy.getUserId());
    if (user == null) {
      throw new IllegalArgumentException("User not found: " + policy.getUserId());
    }
    return repo.save(policy);
  }

  public List<Policy> getAllPolicies() { return repo.findAll(); }

  public List<Policy> getByUser(Long userId) { return repo.findByUserId(userId); }

  @CircuitBreaker(name = "userService", fallbackMethod = "userFallback")
  @Retry(name = "userService")
  public UserClient.UserDto validateUser(Long userId) {
    return userClient.getUserById(userId);
  }

  public UserClient.UserDto userFallback(Long userId, Throwable t) {
    // fallback returns null -> caller handles
    return null;
  }
}

package com.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Claim;

import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
  List<Claim> findByPolicyId(Long policyId);
}

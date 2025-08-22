package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Policy;

import java.util.List;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
  List<Policy> findByUserId(Long userId);
}

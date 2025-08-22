package com.app.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Claim {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long policyId;
  private String claimNumber;
  private String status; // SUBMITTED, UNDER_REVIEW, APPROVED, REJECTED
  private double claimAmount;
  private LocalDate raisedOn;
  // getters/setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public Long getPolicyId() { return policyId; }
  public void setPolicyId(Long policyId) { this.policyId = policyId; }
  public String getClaimNumber() { return claimNumber; }
  public void setClaimNumber(String claimNumber) { this.claimNumber = claimNumber; }
  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }
  public double getClaimAmount() { return claimAmount; }
  public void setClaimAmount(double claimAmount) { this.claimAmount = claimAmount; }
  public LocalDate getRaisedOn() { return raisedOn; }
  public void setRaisedOn(LocalDate raisedOn) { this.raisedOn = raisedOn; }
}

package com.app.model;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Policy {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long userId;
  private String policyNumber;
  private String policyType;
  private double premium;
  private LocalDate startDate;
  private LocalDate endDate;
  // getters/setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public Long getUserId() { return userId; }
  public void setUserId(Long userId) { this.userId = userId; }
  public String getPolicyNumber() { return policyNumber; }
  public void setPolicyNumber(String policyNumber) { this.policyNumber = policyNumber; }
  public String getPolicyType() { return policyType; }
  public void setPolicyType(String policyType) { this.policyType = policyType; }
  public double getPremium() { return premium; }
  public void setPremium(double premium) { this.premium = premium; }
  public LocalDate getStartDate() { return startDate; }
  public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
  public LocalDate getEndDate() { return endDate; }
  public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
}

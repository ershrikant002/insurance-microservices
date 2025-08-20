package com.app.service;

import org.springframework.stereotype.Service;

import com.app.entity.User;
import com.app.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
  private final UserRepository repo;

  public UserService(UserRepository repo) {
    this.repo = repo;
  }

  public User create(User user) {
    // basic check: if email exists, throw IllegalArgumentException
    repo.findByEmail(user.getEmail()).ifPresent(u -> {
      throw new IllegalArgumentException("Email already exists: " + user.getEmail());
    });
    return repo.save(user);
  }

  public List<User> listAll() {
    return repo.findAll();
  }

  public User getById(Long id) {
    return repo.findById(id).orElse(null);
  }

  public User update(Long id, User updated) {
    return repo.findById(id).map(existing -> {
      existing.setFirstName(updated.getFirstName());
      existing.setLastName(updated.getLastName());
      existing.setMobile(updated.getMobile());
      existing.setEmail(updated.getEmail());
      existing.setAddress(updated.getAddress());
      return repo.save(existing);
    }).orElseThrow(() -> new IllegalArgumentException("User not found: " + id));
  }

  public void delete(Long id) {
    repo.deleteById(id);
  }
}

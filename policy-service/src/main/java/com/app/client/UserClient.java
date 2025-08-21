package com.app.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://user-service:9001")
public interface UserClient {
  @GetMapping("/api/users/{id}")
  UserDto getUserById(@PathVariable("id") Long id);

  class UserDto {
    public Long id;
    public String firstName;
    public String lastName;
    public String email;
    public String mobile;
  }
}

package com.bitechain.userservice.controller;

import com.bitechain.userservice.dto.ReadActivationLinkDto;
import com.bitechain.userservice.dto.ReadUserDto;
import com.bitechain.userservice.dto.WriteUserDto;
import com.bitechain.userservice.model.Role;
import com.bitechain.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.UUID;

// DB run command:
// docker run -d \
//  --name pgdb-users \
//  -e POSTGRES_USER=postgres \
//  -e POSTGRES_PASSWORD=postgres \
//  -e POSTGRES_DB=bitechain-usersdb \
//  -p 5432:5432 \
//  postgres:15-alpine

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  // http://localhost:8081/api/v1/users/health
  @GetMapping("/health")
  public ResponseEntity<String> healthCheck() {
    return ResponseEntity
            .status(HttpStatus.OK)
            .header("version", "v1")
            .body("User Service is up and running!");
  }

  // http://localhost:8081/api/v1/users
  @GetMapping("/all")
  public ResponseEntity<List<ReadUserDto>> getAllUsers() {
    var users = userService.getAllUsers();
    return ResponseEntity
            .status(HttpStatus.OK)
            .header("version", "v1")
            .body(users);
  }

  // http://localhost:8081/api/v1/users?role=OWNER
  @GetMapping
  public ResponseEntity<List<ReadUserDto>> getUsersByRole(
          @RequestParam(name = "role") Role role) {
    var users = userService.getUsersByRole(role);

    return ResponseEntity
            .status(HttpStatus.OK)
            .header("version", "v1")
            .body(users);
  }

  // http://localhost:8081/api/v1/users/{id}
  @GetMapping("/{id}")
  public ResponseEntity<ReadUserDto> getUserById(
          @PathVariable String id) {
    var user = userService.getUserById(UUID.fromString(id));

    return ResponseEntity
            .status(HttpStatus.OK)
            .header("version", "v1")
            .body(user);
  }

  // http://localhost:8081/api/v1/users/register/customer
  @PostMapping("/register/customer")
  public ResponseEntity<ReadUserDto> createCustomer(@Valid @RequestBody WriteUserDto customer) {
    var createdUser = userService.createCustomer(customer);

    return ResponseEntity
            .created(URI.create("/api/v1/users/" + createdUser.id()))
            .header("version", "v1")
            .body(createdUser);
  }

  // http://localhost:8081/api/v1/users/register/owner
  @PostMapping("/register/owner")
  public ResponseEntity<ReadUserDto> createOwner(@Valid @RequestBody WriteUserDto owner) {
    var createdUser = userService.createOwner(owner);

    return ResponseEntity
            .created(URI.create("/api/v1/users/" + createdUser.id()))
            .header("version", "v1")
            .body(createdUser);
  }

  // http://localhost:8081/api/v1/users/delete/{id}
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable String id) {
    userService.deleteUser(UUID.fromString(id));
    return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .header("version", "v1")
            .build();
  }

  // http://localhost:8081/api/v1/users/activate/link?email=<email>
  @GetMapping("/activate/link")
  public ResponseEntity<ReadActivationLinkDto> getActivationLink(
          @RequestParam(name = "email") String email) {
    var activationLink = userService.getActivationLink(email);

    return ResponseEntity
            .status(HttpStatus.OK)
            .header("version", "v1")
            .body(activationLink);
  }

  // http://localhost:8081/api/v1/users/activate?token=<activationToken>
  @GetMapping("/activate")
  public ResponseEntity<ReadUserDto> activateUser(
          @RequestParam(name = "token") String token) {
    var activatedUser = userService.activateUser(token);

    return ResponseEntity
            .status(HttpStatus.OK)
            .header("version", "v1")
            .body(activatedUser);
  }
}

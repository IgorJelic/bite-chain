package com.bitechain.userservice.service;

import com.bitechain.userservice.configuration.AppConfiguration;
import com.bitechain.userservice.dto.ReadActivationLinkDto;
import com.bitechain.userservice.dto.ReadUserDto;
import com.bitechain.userservice.dto.WriteUserDto;
import com.bitechain.userservice.exception.UserBadRequestException;
import com.bitechain.userservice.exception.UserConflictException;
import com.bitechain.userservice.exception.UserNotFoundException;
import com.bitechain.userservice.model.Role;
import com.bitechain.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
  private final AppConfiguration appConfiguration;
  private final UserRepository userRepository;

  @Override
  public List<ReadUserDto> getAllUsers() {
    var users = userRepository.findAll();
    return users.stream()
            .map(ReadUserDto::new)
            .toList();
  }

  @Override
  public List<ReadUserDto> getUsersByRole(Role role) {
    var users = userRepository.findAllByRole(role);
    return users.stream()
            .map(ReadUserDto::new)
            .toList();
  }

  @Override
  public ReadUserDto getUserById(UUID id) {
    var user = userRepository.findById(id);
    return user.map(ReadUserDto::new)
            .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
  }

  @Override
  public ReadUserDto createCustomer(WriteUserDto customer) {
    if (userRepository.existsByEmail(customer.email())) {
      throw new UserConflictException("User with email " + customer.email() + " already exists.");
    }

    var user = customer.getCustomer();
    var activationToken = UUID.randomUUID().toString();
    // TODO: Send activation email with token
    user.setActivationToken(activationToken);
    user = userRepository.save(user);

    return new ReadUserDto(user);
  }

  @Override
  public ReadUserDto createOwner(WriteUserDto owner) {
    if (userRepository.existsByEmail(owner.email())) {
      throw new UserConflictException("User with email " + owner.email() + " already exists.");
    }

    var user = owner.getOwner();
    var activationToken = UUID.randomUUID().toString();
    // TODO: Send activation email with token
    user.setActivationToken(activationToken);
    user = userRepository.save(user);

    return new ReadUserDto(user);
  }

  @Override
  public ReadActivationLinkDto getActivationLink(String email) {
    var user = userRepository.findByEmail(email);
    if (user == null) {
      throw new UserNotFoundException("User not found with email: " + email);
    }
    if (user.getActivationToken() == null) {
      throw new UserBadRequestException("User with email " + email + " is already activated.");
    }

    var activationLink = URI.create(appConfiguration.getBaseUrl() + appConfiguration.getRequestMapping() + "/activate?token=" + user.getActivationToken());
    return new ReadActivationLinkDto(activationLink);
  }

  @Override
  public ReadUserDto activateUser(String activationToken) {
    var user = userRepository.findByActivationToken(activationToken);
    if (user == null) {
      throw new UserNotFoundException("User not found with activation token: " + activationToken);
    }

    user.setActive(true);
    user.setActivationToken(null);
    user.setUpdatedAt(Instant.now());
    user = userRepository.save(user);

    return new ReadUserDto(user);
  }

  @Override
  public void deleteUser(UUID id) {
    userRepository.deleteById(id);
  }
}

package com.bitechain.userservice.service;

import com.bitechain.userservice.dto.ReadActivationLinkDto;
import com.bitechain.userservice.dto.ReadUserDto;
import com.bitechain.userservice.dto.ReadUserExistsDto;
import com.bitechain.userservice.dto.WriteUserDto;
import com.bitechain.userservice.model.Role;

import java.util.List;
import java.util.UUID;

public interface UserService {
  List<ReadUserDto> getAllUsers();
  List<ReadUserDto> getUsersByRole(Role role);
  ReadUserDto getUserById(UUID id);
  ReadUserExistsDto userExists(UUID id);
  ReadUserDto createCustomer(WriteUserDto customer);
  ReadUserDto createOwner(WriteUserDto owner);
  ReadActivationLinkDto getActivationLink(String email);
  ReadUserDto activateUser(String activationToken);
  void deleteUser(UUID id);
}

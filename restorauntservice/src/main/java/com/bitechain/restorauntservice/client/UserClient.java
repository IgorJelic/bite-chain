package com.bitechain.restorauntservice.client;

import com.bitechain.restorauntservice.dto.ReadUserExistsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(url = "${user.service.url}")
public interface UserClient {
  @GetMapping("/api/v1/users/exists/{userId}")
  ReadUserExistsDto userExists(@PathVariable UUID userId);
}

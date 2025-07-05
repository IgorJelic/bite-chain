package com.bitechain.restorauntservice.dto;

import org.springframework.http.HttpStatus;

public record ReadProblemDetailDto(
        String title,
        String detail,
        HttpStatus status
) {}

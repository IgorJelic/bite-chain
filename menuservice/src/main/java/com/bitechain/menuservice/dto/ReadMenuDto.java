package com.bitechain.menuservice.dto;

import java.util.List;

public record ReadMenuDto(
        List<ReadItemDto> items
) {
}

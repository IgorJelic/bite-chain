package com.bitechain.menuservice.service;

import com.bitechain.menuservice.dto.ReadItemDto;
import com.bitechain.menuservice.dto.ReadMenuDto;
import com.bitechain.menuservice.dto.WriteItemDto;

import java.util.UUID;

public interface ItemService {
  ReadMenuDto getFilteredMenuItems(UUID restaurantId, String tag, Boolean discount);

  ReadItemDto getItemById(UUID itemId);

  ReadItemDto addMenuItem(WriteItemDto item);

  ReadItemDto updateMenuItem(UUID itemId, WriteItemDto updatedItem);

  void deleteMenuItem(UUID itemId);
}

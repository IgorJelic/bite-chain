package com.bitechain.menuservice.service;

import com.bitechain.menuservice.client.RestaurantClient;
import com.bitechain.menuservice.dto.ReadItemDto;
import com.bitechain.menuservice.dto.ReadMenuDto;
import com.bitechain.menuservice.dto.WriteItemDto;
import com.bitechain.menuservice.exception.ItemNotFoundException;
import com.bitechain.menuservice.exception.RestaurantNotFoundException;
import com.bitechain.menuservice.repository.ItemRepository;
import com.bitechain.menuservice.repository.specification.MenuSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
  private final ItemRepository itemRepository;
  private final RestaurantClient restaurantClient;

  @Override
  public ReadMenuDto getFilteredMenuItems(UUID restaurantId, String tag, Boolean discount) {
    var specification = MenuSpecifications.all()
            .and(MenuSpecifications.byRestaurantId(restaurantId))
            .and(MenuSpecifications.byTag(tag))
            .and(MenuSpecifications.byDiscount(discount));
    var items = itemRepository.findAll(specification);
    return new ReadMenuDto(items.stream()
            .map(ReadItemDto::new)
            .toList());
  }

  @Override
  public ReadItemDto getItemById(UUID itemId) {
    var item = itemRepository.findById(itemId)
            .orElseThrow(() -> new ItemNotFoundException("Item not found with id: " + itemId));
    return new ReadItemDto(item);
  }

  @Override
  public ReadItemDto addMenuItem(WriteItemDto item) {
    var restaurantExists = restaurantClient.restaurantExists(item.restaurantId());
    if (!restaurantExists.exists()) {
      throw new RestaurantNotFoundException("Restaurant with id " + restaurantExists.restaurantId() + " does not exist");
    }
    // TODO: Confirm that user is authorized to add items
    var newItem = itemRepository.save(item.getItem());
    return new ReadItemDto(newItem);
  }

  @Override
  public ReadItemDto updateMenuItem(UUID itemId, WriteItemDto updatedItem) {
    var existingItem = itemRepository.findById(itemId)
            .orElseThrow(() -> new ItemNotFoundException("Item not found with id: " + itemId));

    existingItem.setName(updatedItem.name());
    existingItem.setDescription(updatedItem.description());
    existingItem.setPrice(updatedItem.price());
    existingItem.setImageUrl(updatedItem.imageUrl());
    existingItem.setDiscount(updatedItem.discount());
    existingItem.setAvailable(updatedItem.available());
    existingItem.setTags(updatedItem.tags());

    var savedItem = itemRepository.save(existingItem);
    return new ReadItemDto(savedItem);
  }

  @Override
  public void deleteMenuItem(UUID itemId) {
    var item = itemRepository.findById(itemId)
            .orElseThrow(() -> new ItemNotFoundException("Item not found with id: " + itemId));
    itemRepository.delete(item);
  }
}

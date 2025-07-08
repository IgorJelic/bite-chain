package com.bitechain.menuservice.controller;

import com.bitechain.menuservice.dto.ReadItemDto;
import com.bitechain.menuservice.dto.ReadMenuDto;
import com.bitechain.menuservice.dto.WriteItemDto;
import com.bitechain.menuservice.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

// DB run command:
//docker run -d \
//--name pgdb-menus \
//-e POSTGRES_USER=postgres \
//-e POSTGRES_PASSWORD=postgres \
//-e POSTGRES_DB=bitechain-menusdb \
//-p 5434:5432 \
//postgres:15-alpine

@RestController
@RequestMapping("/api/v1/menu")
@RequiredArgsConstructor
public class MenuController {
  private final ItemService itemService;

  // http://localhost:8083/api/v1/menu/health
  @GetMapping("/health")
  public ResponseEntity<String> healthCheck() {
    return ResponseEntity
            .status(HttpStatus.OK)
            .header("version", "v1")
            .body("Menu Service is up and running!");
  }

  // http://localhost:8083/api/v1/menu?restaurantId={id}
  @GetMapping
  public ResponseEntity<ReadMenuDto> getFilteredMenuItems(
          @RequestParam(name = "restaurantId", required = false) UUID restaurantId,
          @RequestParam(name = "tag", required = false) String tag,
          @RequestParam(name = "discount", required = false) Boolean discount
  ) {
    var items = itemService.getFilteredMenuItems(restaurantId, tag, discount);
    return ResponseEntity
            .status(HttpStatus.OK)
            .header("version", "v1")
            .body(items);
  }

  // http://localhost:8083/api/v1/menu/items/{itemId}
  @GetMapping("/items/{itemId}")
  public ResponseEntity<ReadItemDto> getItemById(@PathVariable UUID itemId) {
    var item = itemService.getItemById(itemId);
    return ResponseEntity
            .status(HttpStatus.OK)
            .header("version", "v1")
            .body(item);
  }

  // http://localhost:8083/api/v1/menu/items
  @PostMapping("/items")
  public ResponseEntity<ReadItemDto> addMenuItem(@Valid @RequestBody WriteItemDto writeItemDto) {
    var item = itemService.addMenuItem(writeItemDto);
    return ResponseEntity
            .created(URI.create("/api/v1/menu/items/" + item.id()))
            .header("version", "v1")
            .body(item);
  }

  // http://localhost:8083/api/v1/menu/items/{itemId}
  @PutMapping("/items/{itemId}")
  public ResponseEntity<ReadItemDto> updateMenuItem(@PathVariable UUID itemId, @Valid @RequestBody WriteItemDto writeItemDto) {
    var item = itemService.updateMenuItem(itemId, writeItemDto);
    return ResponseEntity
            .status(HttpStatus.OK)
            .header("version", "v1")
            .body(item);
  }

  // http://localhost:8083/api/v1/menu/items/{itemId}
  @DeleteMapping("/items/{itemId}")
  public ResponseEntity<Void> deleteMenuItem(@PathVariable UUID itemId) {
    itemService.deleteMenuItem(itemId);
    return ResponseEntity
            .noContent()
            .header("version", "v1")
            .build();
  }
}

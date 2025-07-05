package com.bitechain.restorauntservice.controller;

import com.bitechain.restorauntservice.dto.ReadRestaurantDto;
import com.bitechain.restorauntservice.dto.ReadWorkingHourDto;
import com.bitechain.restorauntservice.dto.WriteRestaurantDto;
import com.bitechain.restorauntservice.dto.WriteWorkingHourDto;
import com.bitechain.restorauntservice.service.RestaurantService;
import com.bitechain.restorauntservice.service.WorkingHoursService;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//DB run command:
//docker run -d \
//--name pgdb-restaurants \
//-e POSTGRES_USER=postgres \
//-e POSTGRES_PASSWORD=postgres \
//-e POSTGRES_DB=bitechain-restorauntdb \
//-p 5432:5432 \
//postgres:15-alpine

@RestController
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
  private final RestaurantService restaurantService;
  private final WorkingHoursService workingHoursService;

  // http://localhost:8082/api/v1/restaurants/health
  @GetMapping("/health")
  public ResponseEntity<String> healthCheck() {
    return ResponseEntity
            .status(HttpStatus.OK)
            .header("version", "v1")
            .body("Restaurant Service is up and running!");
  }

  // http://localhost:8082/api/v1/restaurants?ownerId=<UUID>&city=<city>
  @GetMapping
  public ResponseEntity<List<ReadRestaurantDto>> getRestaurants(
          @RequestParam(name = "ownerId", required = false)UUID ownerId,
          @RequestParam(name = "city", required = false)String city
          ) {
    var restaurants = restaurantService.listRestaurants(
            Optional.ofNullable(ownerId),
            Optional.ofNullable(city));

    return ResponseEntity
            .status(HttpStatus.OK)
            .header("version", "v1")
            .body(restaurants);
  }

  // http://localhost:8082/api/v1/restaurants/<id>
  @GetMapping("/{id}")
  public ResponseEntity<ReadRestaurantDto> getRestaurantById(@PathVariable UUID id) {
    var restaurant = restaurantService.getRestaurantById(id);
    return ResponseEntity
            .status(HttpStatus.OK)
            .header("version", "v1")
            .body(restaurant);
  }

  // http://localhost:8082/api/v1/restaurants/name/<name>
  @GetMapping("/name/{name}")
  public ResponseEntity<ReadRestaurantDto> getRestaurantByName(@PathVariable String name) {
    var restaurant = restaurantService.getRestaurantByName(name);
    return ResponseEntity
            .status(HttpStatus.OK)
            .header("version", "v1")
            .body(restaurant);
  }

  // http://localhost:8082/api/v1/restaurants
  @PostMapping
  public ResponseEntity<ReadRestaurantDto> createRestaurant(@RequestBody WriteRestaurantDto restaurantDto) {
    var createdRestaurant = restaurantService.createRestaurant(restaurantDto);
    return ResponseEntity
            .created(URI.create("/api/v1/restaurants/" + createdRestaurant.id()))
            .header("version", "v1")
            .body(createdRestaurant);
  }

  // http://localhost:8082/api/v1/restaurants/<id>
  @PutMapping("/{id}")
  public ResponseEntity<ReadRestaurantDto> updateRestaurant(
          @PathVariable UUID id,
          @RequestBody WriteRestaurantDto restaurantDto) {
    var updatedRestaurant = restaurantService.updateRestaurant(id, restaurantDto);
    return ResponseEntity
            .status(HttpStatus.OK)
            .header("version", "v1")
            .body(updatedRestaurant);
  }

  // http://localhost:8082/api/v1/restaurants/<id>
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteRestaurant(@PathVariable UUID id) {
    restaurantService.deleteRestaurant(id);
    return ResponseEntity
            .noContent()
            .header("version", "v1")
            .build();
  }

  // Working Hours Endpoints
  // http://localhost:8082/api/v1/restaurants/{restaurantId}/working-hours
  @GetMapping("/{restaurantId}/working-hours")
  public ResponseEntity<List<ReadWorkingHourDto>> getWorkingHours(
          @PathVariable UUID restaurantId) {
    var workingHours = restaurantService.getWorkingHours(restaurantId);
    return ResponseEntity
            .status(HttpStatus.OK)
            .header("version", "v1")
            .body(workingHours);
  }

  // http://localhost:8082/api/v1/restaurants/{restaurantId}/working-hours
  @PostMapping("/{restaurantId}/working-hours")
  public ResponseEntity<ReadWorkingHourDto> addWorkingHour(
          @PathVariable UUID restaurantId,
          @Valid @RequestBody WriteWorkingHourDto dto) {
    var createdWorkingHour = workingHoursService.addWorkingHour(restaurantId, dto);
    return ResponseEntity
            .created(URI.create("/api/v1/restaurants/" + restaurantId + "/working-hours/" + createdWorkingHour.id()))
            .header("version", "v1")
            .body(createdWorkingHour);
  }

  // http://localhost:8082/api/v1/restaurants/{restaurantId}/working-hours/{hourId}
  @PutMapping("/working-hours/{hourId}")
  public ResponseEntity<ReadWorkingHourDto> updateWorkingHour(
          @PathVariable UUID hourId,
          @Valid @RequestBody WriteWorkingHourDto dto) {
    var updatedWorkingHour = workingHoursService.updateWorkingHour(hourId, dto);
    return ResponseEntity
            .status(HttpStatus.OK)
            .header("version", "v1")
            .body(updatedWorkingHour);
  }

  // http://localhost:8082/api/v1/restaurants/{restaurantId}/working-hours/{hourId}
  @DeleteMapping("/working-hours/{hourId}")
  public ResponseEntity<Void> deleteWorkingHour(
          @PathVariable UUID hourId) {
    workingHoursService.deleteWorkingHour(hourId);
    return ResponseEntity
            .noContent()
            .header("version", "v1")
            .build();
  }

}

package com.bitechain.restorauntservice.repository.specification;

import com.bitechain.restorauntservice.model.Restaurant;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class RestaurantSpecifications {
  public static Specification<Restaurant> all() {
    return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
  }

  public static Specification<Restaurant> byOwnerId(UUID ownerId) {
    return (root, query, criteriaBuilder) -> {
      if (ownerId == null) {
        return criteriaBuilder.conjunction();
      }
      return criteriaBuilder.equal(root.get("ownerId"), ownerId);
    };
  }

  public static Specification<Restaurant> byCity(String city) {
    return (root, query, criteriaBuilder) -> {
      if (city == null || city.isEmpty()) {
        return criteriaBuilder.conjunction();
      }
      return criteriaBuilder.equal(root.get("address").get("city"), city);
    };
  }
}

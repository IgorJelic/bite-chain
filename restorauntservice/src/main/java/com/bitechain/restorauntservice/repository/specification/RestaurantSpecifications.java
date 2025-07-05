package com.bitechain.restorauntservice.repository.specification;

import com.bitechain.restorauntservice.model.Restaurant;
import org.springframework.data.jpa.domain.Specification;

public class RestaurantSpecifications {
  public static Specification<Restaurant> all() {
    return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
  }

  public static Specification<Restaurant> byOwnerId(String ownerId) {
    return (root, query, criteriaBuilder)
            -> criteriaBuilder.equal(root.get("ownerId"), ownerId);
  }

  public static Specification<Restaurant> byCity(String city) {
    return (root, query, criteriaBuilder)
            -> criteriaBuilder.equal(root.get("address").get("city"), city);
  }
}

package com.bitechain.menuservice.repository.specification;

import com.bitechain.menuservice.model.Item;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class MenuSpecifications {
  public static Specification<Item> all() {
    return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
  }

  public static Specification<Item> byRestaurantId(UUID restaurantId) {
    return (root, query, criteriaBuilder) -> {
      if (restaurantId == null) {
        return criteriaBuilder.conjunction();
      }
      return criteriaBuilder.equal(root.get("restaurantId"), restaurantId);
    };
  }

  public static Specification<Item> byTag(String tag) {
    return (root, query, criteriaBuilder) -> {
      if (tag == null || tag.isEmpty()) {
        return criteriaBuilder.conjunction();
      }
      return criteriaBuilder.isMember(tag, root.get("tags"));
    };
  }

  public static Specification<Item> byDiscount(Boolean discount) {
    return (root, query, criteriaBuilder) -> {
      if (discount == null) {
        return criteriaBuilder.conjunction();
      }
      return criteriaBuilder.greaterThan(root.get("discount"), 0);
    };
  }
}

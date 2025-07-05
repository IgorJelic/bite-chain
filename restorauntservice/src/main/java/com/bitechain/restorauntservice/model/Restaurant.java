package com.bitechain.restorauntservice.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "restaurants")
@Data
public class Restaurant {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  @Column(unique = true)
  private String name;
  private String description;
  private Address address;
  private String website;
  @Column(name = "image_url")
  private String imageUrl;

  @Column(name = "owner_id", nullable = false)
  private UUID ownerId; // Reference to the owner (User) of the restaurant

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "restaurant_id", nullable = false)
  private Set<WorkingHour> workingHours = new HashSet<>();

  public void addWorkingHour(WorkingHour workingHour) {
    boolean dayExists = workingHours.stream().anyMatch(wh -> wh.getDayOfWeek() == workingHour.getDayOfWeek());
    if (dayExists) {
      throw new IllegalArgumentException("Restaurant[" + this.id + "] Working hour for " + workingHour.getDayOfWeek() + " already exists.");
    }

    workingHours.add(workingHour);
  }
}

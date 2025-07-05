package com.bitechain.restorauntservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Address {
  private String formattedAddress;
  private String street;
  private String city;
  private String country;
  @Column(name = "postal_code")
  private String postalCode;
  private Double latitude;
  private Double longitude;
}

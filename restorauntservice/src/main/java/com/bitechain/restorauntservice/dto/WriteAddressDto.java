package com.bitechain.restorauntservice.dto;

import com.bitechain.restorauntservice.model.Address;
import jakarta.validation.constraints.NotBlank;

public record WriteAddressDto(
        @NotBlank
        String street,
        @NotBlank
        String city,
        @NotBlank
        String country,
        @NotBlank
        String postalCode,
        Double latitude,
        Double longitude
) {
  public Address getAddress() {
    Address address = new Address();
    address.setFormattedAddress(String.format("%s, %s, %s, %s", street, city, country, postalCode));
    address.setStreet(street);
    address.setCity(city);
    address.setCountry(country);
    address.setPostalCode(postalCode);
    address.setLatitude(latitude);
    address.setLongitude(longitude);
    return address;
  }
}

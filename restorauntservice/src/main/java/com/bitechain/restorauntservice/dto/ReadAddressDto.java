package com.bitechain.restorauntservice.dto;

import com.bitechain.restorauntservice.model.Address;

public record ReadAddressDto(
  String formattedAddress,
  String street,
  String city,
  String country,
  String postalCode,
  Double latitude,
  Double longitude
) {
  public ReadAddressDto(Address address) {
    this(
      address.getFormattedAddress(),
      address.getStreet(),
      address.getCity(),
      address.getCountry(),
      address.getPostalCode(),
      address.getLatitude(),
      address.getLongitude()
    );
  }
}

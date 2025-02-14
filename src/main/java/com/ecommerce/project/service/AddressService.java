package com.ecommerce.project.service;

import com.ecommerce.project.model.User;
import com.ecommerce.project.payload.AddressDto;

public interface AddressService {
    AddressDto createAddress(AddressDto addressDto, User user);
}

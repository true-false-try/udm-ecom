package com.ecommerce.project.controller;

import com.ecommerce.project.model.User;
import com.ecommerce.project.payload.AddressDto;
import com.ecommerce.project.service.AddressService;
import com.ecommerce.project.util.AuthUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api")
public class AddressController {

    private final AuthUtil authUtil;
    private final AddressService addressService;

    public ResponseEntity<AddressDto> createAddress(AddressDto addressDto) {
        User user = authUtil.loggedInUser();
        AddressDto saveAddressDto = addressService.createAddress(addressDto, user);
        return ResponseEntity.ok().body(saveAddressDto);
    }


}

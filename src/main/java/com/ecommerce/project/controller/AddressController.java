package com.ecommerce.project.controller;

import com.ecommerce.project.model.User;
import com.ecommerce.project.payload.AddressDto;
import com.ecommerce.project.service.AddressService;
import com.ecommerce.project.util.AuthUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api")
public class AddressController {

    private final AuthUtil authUtil;
    private final AddressService addressService;


    @PostMapping("addresses")
    public ResponseEntity<AddressDto> createAddress(@Valid @RequestBody AddressDto addressDto) {
        User user = authUtil.loggedInUser();
        AddressDto saveAddressDto = addressService.createAddress(addressDto, user);
        return ResponseEntity.ok().body(saveAddressDto);
    }

    @GetMapping("adresses")
    public ResponseEntity<List<AddressDto>> getAddresses() {
        List<AddressDto> addressDtos = addressService.getAddresses();
        return ResponseEntity.ok().body(addressDtos);
    }


}

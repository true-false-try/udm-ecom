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

    @GetMapping("addresses")
    public ResponseEntity<List<AddressDto>> getAddresses() {
        List<AddressDto> addressDtos = addressService.getAddresses();
        return ResponseEntity.ok().body(addressDtos);
    }

    @GetMapping("address/{addressId}")
    public ResponseEntity<AddressDto> getAddressId(@PathVariable Long addressId) {
        AddressDto result = addressService.getAddressId(addressId);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("addresses")
    public ResponseEntity<List<AddressDto>> getUserAddresses() {
        User user = authUtil.loggedInUser();
        List<AddressDto> result = addressService.getUserAddresses(user);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("address/{addressId}")
    public ResponseEntity<AddressDto> updateAddressId(@PathVariable Long addressId,
                                                      @RequestBody AddressDto addressDto) {
        AddressDto result = addressService.updateAddressId(addressId, addressDto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("address/{addressId}")
    public ResponseEntity<String> deleteAddressId(@PathVariable Long addressId) {
        String result = addressService.deleteAddressId(addressId);
        return ResponseEntity.ok().body(result);
    }


}

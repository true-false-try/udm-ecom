package com.ecommerce.project.service.impl;

import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Address;
import com.ecommerce.project.model.User;
import com.ecommerce.project.payload.AddressDto;
import com.ecommerce.project.repositories.AddressRepository;
import com.ecommerce.project.repositories.UserRepository;
import com.ecommerce.project.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Override
    public AddressDto createAddress(AddressDto addressDto, User user) {
        Address address = modelMapper.map(addressDto, Address.class);
        List<Address> addresses =  user.getAddresses();
        addresses.add(address);

        user.setAddresses(addresses);
        address.setUser(user);
        Address saveAddress = addressRepository.save(address);
        return modelMapper.map(saveAddress, AddressDto.class);
    }

    @Override
    public List<AddressDto> getAddresses() {
        List<Address> addresses = addressRepository.findAll();
        return addresses.stream()
                .map(address -> modelMapper.map(address, AddressDto.class))
                .toList();
    }

    @Override
    public AddressDto getAddressId(Long addressId) {
        Address address = addressRepository.getAddressByAddressId(addressId);
        return modelMapper.map(address, AddressDto.class);
    }

    @Override
    public List<AddressDto> getUserAddresses(User user) {
        List<Address> addresses = user.getAddresses();
        return addresses.stream()
                .map(address -> modelMapper.map(address, AddressDto.class))
                .toList();
    }

    @Override
    public AddressDto updateAddressId(Long addressId, AddressDto addressDto) {
         Address addressFromDatabase = addressRepository.getAddressByAddressId(addressId);
         addressFromDatabase.setCity(addressDto.getCity());
         addressFromDatabase.setPinCode(addressDto.getPincode());
         addressFromDatabase.setState(addressDto.getState());
         addressFromDatabase.setCountry(addressDto.getCountry());
         addressFromDatabase.setStreet(addressDto.getStreet());
         addressFromDatabase.setBuildingName(addressDto.getBuildingName());

         Address updateAddress = addressRepository.save(addressFromDatabase);
         User user = addressFromDatabase.getUser();
         user.getAddresses().removeIf(address ->  address.getAddressId().equals(addressId));
         user.getAddresses().add(updateAddress);
         userRepository.save(user);
        return modelMapper.map(updateAddress, AddressDto.class);
    }

    @Override
    public String deleteAddressId(Long addressId) {
        Address addressFromDatabase = addressRepository.findById(addressId)
                        .orElseThrow(() -> new ResourceNotFoundException("Address", "addressId", addressId));

        User user  = addressFromDatabase.getUser();
        user.getAddresses().removeIf(address -> address.getAddressId().equals(addressId));
        userRepository.save(user);
        addressRepository.delete(addressFromDatabase);
        return "Address deleted successfully with addressId: " + addressId;
    }
}

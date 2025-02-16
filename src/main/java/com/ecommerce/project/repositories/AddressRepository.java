package com.ecommerce.project.repositories;

import com.ecommerce.project.model.Address;
import com.ecommerce.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address getAddressByAddressId(Long addressId);

    List<Address> getAddressByUser(User user);

    List<Address> findAddressByAddressId(Long addressId);

    void deleteAddressByAddressId(Long addressId);
}

package com.springproject.demo.service;

import com.springproject.demo.model.Address;
import com.springproject.demo.model.UserModel;
import com.springproject.demo.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public List<Address> findByCity(String city) {
        return addressRepository.findByCity(city);
    }

    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }

    public Address getAddressById(Long id) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        return addressOptional.orElse(null);
    }

    public Address updateAddress(Address address, Long id) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        if (addressOptional.isPresent()) {
            address.setId(id);
            return addressRepository.save(address);
        } else {
            return null;
        }
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

    // public UserModel getUserById(Long userId) throws NotFoundException {
    //     Optional<UserModel> userOptional = userService.findById(userId);
    //     if (userOptional.isPresent()) {
    //         return userOptional.get();
    //     } else {
    //         // Handle the case when the user with the given ID is not found
    //         throw new NotFoundException();
    //     }
    // }
}

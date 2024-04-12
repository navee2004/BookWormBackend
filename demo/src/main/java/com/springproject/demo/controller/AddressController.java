package com.springproject.demo.controller;

import com.springproject.demo.model.Address;
import com.springproject.demo.model.UserModel;
import com.springproject.demo.service.AddressService;
import com.springproject.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserService userService;
    @GetMapping
    public List<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    // @PostMapping
    // public ResponseEntity<Address> addAddress(@RequestBody Address address) {
    //     Address newAddress = addressService.addAddress(address);
    //     return new ResponseEntity<>(newAddress, HttpStatus.CREATED);
    // }

    @PostMapping("/address/{userId}/create")
    public ResponseEntity<Address> addAddress(@PathVariable Long userId, @RequestBody Address address) throws NotFoundException {
        UserModel user = userService.getUserById1(userId);
        if (user == null) {
            // Handle the case where the user does not exist
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Set the user for the order
        address.setUser(user);

        Address savedOrder = addressService.addAddress(address);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        Address address = addressService.getAddressById(id);
        if (address != null) {
            return new ResponseEntity<>(address, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/city/{city}")
    public ResponseEntity<List<Address>> getAddressByCity(@PathVariable String city) {
        List<Address> addresses = addressService.findByCity(city);
        if (!addresses.isEmpty()) {
            return new ResponseEntity<>(addresses, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@RequestBody Address address, @PathVariable Long id) {
        Address updatedAddress = addressService.updateAddress(address, id);
        if (updatedAddress != null) {
            return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

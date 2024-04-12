package com.springproject.demo.repository;

import com.springproject.demo.model.Address;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    
    // Custom query to find addresses by city
    @Query("SELECT a FROM Address a WHERE a.city = ?1")
    List<Address> findByCity(String city);
}

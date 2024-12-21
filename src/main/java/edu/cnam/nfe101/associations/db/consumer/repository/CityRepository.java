package edu.cnam.nfe101.associations.db.consumer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.cnam.nfe101.associations.db.consumer.model.City;


public interface CityRepository extends JpaRepository<City, Integer> {
    
    Optional<City> findByPostalCode(String postalCode);
}

package edu.cnam.nfe101.associations.db.consumer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import edu.cnam.nfe101.associations.db.consumer.model.Association;


public interface AssociationRepository extends JpaRepository<Association, Integer> {
    
    @EntityGraph(attributePaths = {"city"})
    Optional<Association> findById(Integer id);

    Optional<Association> findByName(String name);
}

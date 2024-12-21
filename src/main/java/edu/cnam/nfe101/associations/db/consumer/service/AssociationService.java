package edu.cnam.nfe101.associations.db.consumer.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import edu.cnam.nfe101.associations.db.consumer.dto.AssociationJson;
import edu.cnam.nfe101.associations.db.consumer.model.Association;
import edu.cnam.nfe101.associations.db.consumer.model.City;
import edu.cnam.nfe101.associations.db.consumer.repository.AssociationRepository;
import edu.cnam.nfe101.associations.db.consumer.repository.CityRepository;

@Service
public class AssociationService {
    
    private static final Logger log = LoggerFactory.getLogger(AssociationService.class);

    private final AssociationRepository associationRepository;
    private final CityRepository cityRepository;

    public AssociationService(AssociationRepository associationRepository, CityRepository cityRepository) {
        this.associationRepository = associationRepository;
        this.cityRepository = cityRepository;
    }

    public void save(AssociationJson json) {
        City city;
        try {
            city = getOrSaveCity(json);
        } catch (Exception e) {
            log.error("Error getting/saving city from association object. City name: {}, postal code: {}", json.getCity(), json.getPostalCode(), e);
            throw new RuntimeException(e);
        }
        
        try {
            saveAssociation(json, city);
        } catch (Exception e) {
            log.error("Error saving association object in DB {}", json, e);
            throw new RuntimeException(e);
        }
        

    }

    private void saveAssociation(AssociationJson json, City city) {
        Association association = associationRepository.findByName(json.getName())
                        .orElseGet(() -> new Association());
        association.setCity(city);
        association.setName(json.getName());
        association.setActDomain(json.getActDomain());
        association.setActSectorLabel(json.getActSectorLabel());
        association.setAudience(json.getAudience());
        association.setGeoSector(json.getGeoSector());
        association.setExternalId(json.getExternalId() + "");
        associationRepository.save(association);
    }

    private City getOrSaveCity(AssociationJson json) {
        String postalCode = Optional.ofNullable(json.getPostalCode())
        .map(s -> s.trim())
        .orElse("N/A");
        Optional<City> byPostalCode = cityRepository.findByPostalCode(postalCode);
        City city = byPostalCode.orElseGet(() -> {
            City newCity = new City();
            newCity.setName(json.getCity());
            newCity.setPostalCode(postalCode);
            return cityRepository.save(newCity);
        });
        return city;
    }
}

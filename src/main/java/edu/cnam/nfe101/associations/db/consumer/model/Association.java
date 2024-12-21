package edu.cnam.nfe101.associations.db.consumer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "associations")
public class Association {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "association_id")
    private Integer associationId;

    @Column(name = "association_name")
    private String name;
    @Column(name = "association_domain_name")
    private String actDomain;
    @Column(name = "association_sector_label")
    private String actSectorLabel;
    @Column(name = "association_target_audience")
    private String audience;
    @Column(name = "association_geo_sector")
    private String geoSector;
    @Column(name = "external_id")
    private String externalId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    
    @Override
    public String toString() {
        return "Association [associationId=" + associationId + ", name=" + name + "]";
    }

    public Integer getAssociationId() {
        return associationId;
    }

    public void setAssociationId(Integer associationId) {
        this.associationId = associationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActDomain() {
        return actDomain;
    }

    public void setActDomain(String actDomain) {
        this.actDomain = actDomain;
    }

    public String getActSectorLabel() {
        return actSectorLabel;
    }

    public void setActSectorLabel(String actSectorLabel) {
        this.actSectorLabel = actSectorLabel;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public String getGeoSector() {
        return geoSector;
    }

    public void setGeoSector(String geoSector) {
        this.geoSector = geoSector;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }


    
}

package edu.cnam.nfe101.associations.db.consumer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AssociationJson {

    private String name;
    private String postalCode;
    private String city;
    private String actDomain;
    private String actSectorLabel;
    private String audience;
    private String geoSector;
    private Integer externalId;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
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
    public Integer getExternalId() {
        return externalId;
    }
    public void setExternalId(Integer externalId) {
        this.externalId = externalId;
    }


    
}

package dtos.hoteldto;

class AddressDTO {
    String streetAddress;
    String locality;
    String postalCode;
    String region;
    String countryName;
    String countryCode;

    public AddressDTO(String streetAddress, String locality, String postalCode, String region, String countryName, String countryCode) {
        this.streetAddress = streetAddress;
        this.locality = locality;
        this.postalCode = postalCode;
        this.region = region;
        this.countryName = countryName;
        this.countryCode = countryCode;
    }

    public AddressDTO() {
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "AddressDTO{" + "streetAddress=" + streetAddress + ", locality=" + locality + ", postalCode=" + postalCode + ", region=" + region + ", countryName=" + countryName + ", countryCode=" + countryCode + '}';
    }
    
    
}

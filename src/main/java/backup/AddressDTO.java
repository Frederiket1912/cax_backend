package backup;


class AddressDTO {

    private String countryName;
    private String cityName;
    private String postalCode;
    private String provinceName;
    private String addressLine1;
    private String countryCode;
    private String fullAddress;

    public AddressDTO() {
    }

    public AddressDTO(String countryName, String cityName, String postalCode, String provinceName, String addressLine1, String countryCode, String fullAddress) {
        this.countryName = countryName;
        this.cityName = cityName;
        this.postalCode = postalCode;
        this.provinceName = provinceName;
        this.addressLine1 = addressLine1;
        this.countryCode = countryCode;
        this.fullAddress = fullAddress;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    @Override
    public String toString() {
        return "Address{" + "countryName=" + countryName + ", cityName=" + cityName + ", postalCode=" + postalCode + ", provinceName=" + provinceName + ", addressLine1=" + addressLine1 + ", countryCode=" + countryCode + ", fullAddress=" + fullAddress + '}';
    }

    
    
}

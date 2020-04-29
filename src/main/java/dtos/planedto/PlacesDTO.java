package dtos.planedto;

class PlacesDTO {

    int PlaceId;
    String IataCode;
    String Name;
    String CityName;
    String CountryName;

    public PlacesDTO() {
    }

    public PlacesDTO(int PlaceId, String IataCode, String Name, String CityName, String CountryName) {
        this.PlaceId = PlaceId;
        this.IataCode = IataCode;
        this.Name = Name;
        this.CityName = CityName;
        this.CountryName = CountryName;
    }

    public int getPlaceId() {
        return PlaceId;
    }

    public void setPlaceId(int PlaceId) {
        this.PlaceId = PlaceId;
    }

    public String getIataCode() {
        return IataCode;
    }

    public void setIataCode(String IataCode) {
        this.IataCode = IataCode;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String CityName) {
        this.CityName = CityName;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String CountryName) {
        this.CountryName = CountryName;
    }

    @Override
    public String toString() {
        return "PlacesDTO{" + "PlaceId=" + PlaceId + ", IataCode=" + IataCode + ", Name=" + Name + ", CityName=" + CityName + ", CountryName=" + CountryName + '}';
    }
    
    
            
}

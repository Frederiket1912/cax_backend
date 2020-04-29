package dtos.hoteldto;

class ResultsDTO {
    String id;
    String name;
    String thumbnailUrl;
    String starRating;
    AddressDTO address;
    String supplierHotelId;

    public ResultsDTO() {
    }

    public ResultsDTO(String id, String name, String thumbnailUrl, String starRating, AddressDTO address, String supplierHotelId) {
        this.id = id;
        this.name = name;
        this.thumbnailUrl = thumbnailUrl;
        this.starRating = starRating;
        this.address = address;
        this.supplierHotelId = supplierHotelId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getStarRating() {
        return starRating;
    }

    public void setStarRating(String starRating) {
        this.starRating = starRating;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public String getSupplierHotelId() {
        return supplierHotelId;
    }

    public void setSupplierHotelId(String supplierHotelId) {
        this.supplierHotelId = supplierHotelId;
    }

    @Override
    public String toString() {
        return "ResultsDTO{" + "id=" + id + ", name=" + name + ", thumbnailUrl=" + thumbnailUrl + ", starRating=" + starRating + ", address=" + address + ", supplierHotelId=" + supplierHotelId + '}';
    }
    
    
}

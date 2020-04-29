
package backup;


public class GuestReviewsDTO {
    BrandsDTO brands;

    public GuestReviewsDTO() {
    }

    public GuestReviewsDTO(BrandsDTO brands) {
        this.brands = brands;
    }

    public BrandsDTO getBrands() {
        return brands;
    }

    public void setBrands(BrandsDTO brands) {
        this.brands = brands;
    }

    @Override
    public String toString() {
        return "GuestReviewsDTO{" + "brands=" + brands + '}';
    }
    
    
    
}

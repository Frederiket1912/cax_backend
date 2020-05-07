package dtos.hoteldto;


public class RatePlanDTO {
    PriceDTO price;

    public RatePlanDTO() {
    }
    
    
    public RatePlanDTO(PriceDTO price) {
        this.price = price;
    }

    public PriceDTO getPrice() {
        return price;
    }

    public void setPrice(PriceDTO price) {
        this.price = price;
    }
}

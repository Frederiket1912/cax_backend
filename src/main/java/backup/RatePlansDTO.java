
package backup;

import java.util.ArrayList;


public class RatePlansDTO {
    PriceDTO price;

    public RatePlansDTO() {
    }

    public RatePlansDTO(PriceDTO price) {
        this.price = price;
    }

    public PriceDTO getRatePlans() {
        return price;
    }

    public void setRatePlans(PriceDTO price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "RatePlansDTO{" + "ratePlans=" + price + '}';
    }

    
    
    
}

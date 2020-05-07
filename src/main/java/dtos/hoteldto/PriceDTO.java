
package dtos.hoteldto;


public class PriceDTO {
    String current;

    public PriceDTO() {
    }
    
    
    public PriceDTO(String exactCurrent) {
        this.current = exactCurrent;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }
    
}

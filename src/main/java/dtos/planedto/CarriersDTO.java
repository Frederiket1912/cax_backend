package dtos.planedto;


public class CarriersDTO {
    int CarrierId;
    String Name;

    public CarriersDTO() {
    }

    public CarriersDTO(int CarrierId, String Name) {
        this.CarrierId = CarrierId;
        this.Name = Name;
    }

    public int getCarrierId() {
        return CarrierId;
    }

    public void setCarrierId(int CarrierId) {
        this.CarrierId = CarrierId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    @Override
    public String toString() {
        return "CarriersDTO{" + "CarrierId=" + CarrierId + ", Name=" + Name + '}';
    }
    
    
}

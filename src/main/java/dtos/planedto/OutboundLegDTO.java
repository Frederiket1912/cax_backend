package dtos.planedto;

import java.util.ArrayList;


class OutboundLegDTO {
    ArrayList<Integer> CarrierIds;
    int OriginId;
    int DestinationId;
    String DepartureDate;

    public OutboundLegDTO() {
    }

    public OutboundLegDTO(ArrayList<Integer> CarrierIds, int OriginId, int DestinationId, String DepartureDate) {
        this.CarrierIds = CarrierIds;
        this.OriginId = OriginId;
        this.DestinationId = DestinationId;
        this.DepartureDate = DepartureDate;
    }

    public ArrayList<Integer> getCarrierIds() {
        return CarrierIds;
    }

    public void setCarrierIds(ArrayList<Integer> CarrierIds) {
        this.CarrierIds = CarrierIds;
    }

    public int getOriginId() {
        return OriginId;
    }

    public void setOriginId(int OriginId) {
        this.OriginId = OriginId;
    }

    public int getDestinationId() {
        return DestinationId;
    }

    public void setDestinationId(int DestinationId) {
        this.DestinationId = DestinationId;
    }

    public String getDepartureDate() {
        return DepartureDate;
    }

    public void setDepartureDate(String DepartureDate) {
        this.DepartureDate = DepartureDate;
    }

    @Override
    public String toString() {
        return "OutboundLegDTO{" + "CarrierIds=" + CarrierIds + ", OriginId=" + OriginId + ", DestinationId=" + DestinationId + ", DepartureDate=" + DepartureDate + '}';
    }
    
    
}

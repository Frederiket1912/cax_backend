package dtos.planedto;

import java.util.ArrayList;


public class DatesDTO {
    ArrayList<OutboundDatesDTO> OutboundDates;
    ArrayList<InboundDatesDTO> InboundDates; 

    public DatesDTO() {
    }

    public DatesDTO(ArrayList<OutboundDatesDTO> OutboundDates, ArrayList<InboundDatesDTO> InboundDates) {
        this.OutboundDates = OutboundDates;
        this.InboundDates = InboundDates;
    }

    public ArrayList<OutboundDatesDTO> getOutboundDates() {
        return OutboundDates;
    }

    public void setOutboundDates(ArrayList<OutboundDatesDTO> OutboundDates) {
        this.OutboundDates = OutboundDates;
    }

    public ArrayList<InboundDatesDTO> getInboundDates() {
        return InboundDates;
    }

    public void setInboundDates(ArrayList<InboundDatesDTO> InboundDates) {
        this.InboundDates = InboundDates;
    }

    @Override
    public String toString() {
        return "DatesDTO{" + "OutboundDates=" + OutboundDates + ", InboundDates=" + InboundDates + '}';
    }
    
    
}

package dtos.supportticketdto;

import entities.TicketChain;
import java.util.List;

public class ReplySupportTicketDTO {

    int supportid;
    List<TicketChain> supporttickets;

    public ReplySupportTicketDTO() {
    }

    public ReplySupportTicketDTO(int supportid, List<TicketChain> supporttickets) {
        this.supportid = supportid;
        this.supporttickets = supporttickets;
    }

    public int getSupportid() {
        return supportid;
    }

    public void setSupportid(int supportid) {
        this.supportid = supportid;
    }

    public List<TicketChain> getSupporttickets() {
        return supporttickets;
    }

    public void setSupporttickets(List<TicketChain> supporttickets) {
        this.supporttickets = supporttickets;
    }

    @Override
    public String toString() {
        return "ReplySupportTicketDTO{" + "supportid=" + supportid + ", supporttickets=" + supporttickets + '}';
    }

    
    
    

}

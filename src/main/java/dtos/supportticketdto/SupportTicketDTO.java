package dtos.supportticketdto;

import entities.SupportTicket;
import java.util.List;

public class SupportTicketDTO {

    String username;
    List<SupportTicket> supporttickets;

    public SupportTicketDTO() {
    }

    public SupportTicketDTO(String username, List<SupportTicket> supporttickets) {
        this.username = username;
        this.supporttickets = supporttickets;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<SupportTicket> getSupporttickets() {
        return supporttickets;
    }

    public void setSupporttickets(List<SupportTicket> supporttickets) {
        this.supporttickets = supporttickets;
    }

    @Override
    public String toString() {
        return "SupportTicketDTO{" + "username=" + username + ", supporttickets=" + supporttickets + '}';
    }

    
    
    

}

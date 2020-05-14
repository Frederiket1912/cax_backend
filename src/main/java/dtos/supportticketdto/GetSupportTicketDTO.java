package dtos.supportticketdto;

import entities.SupportTicket;
import entities.TicketChain;
import java.util.List;

public class GetSupportTicketDTO {
    
    private int ticket_id;
    private List<TicketChain> ticketchain;

    public GetSupportTicketDTO() {
    }

    public GetSupportTicketDTO(int ticket_id, List<TicketChain> ticketchain) {
        this.ticket_id = ticket_id;
        this.ticketchain = ticketchain;
    }
    
    public GetSupportTicketDTO(SupportTicket supportticket) {
        this.ticket_id = supportticket.getId();
        this.ticketchain = supportticket.getTicketchain();
    }

    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public List<TicketChain> getTicketchain() {
        return ticketchain;
    }

    public void setTicketchain(List<TicketChain> ticketchain) {
        this.ticketchain = ticketchain;
    }

    @Override
    public String toString() {
        return "GetSupportTicketDTO{" + "ticket_id=" + ticket_id + ", ticketchain=" + ticketchain + '}';
    }

    
}

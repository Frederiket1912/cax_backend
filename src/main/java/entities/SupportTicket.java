package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(name = "SupportTicket.deleteAllRows", query = "DELETE from SupportTicket")
public class SupportTicket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private int id;
    @ManyToMany (cascade = CascadeType.PERSIST)
    private List<TicketChain> ticketchain;
    private Boolean ticketOpen = true;

    public SupportTicket() {
    }

    public SupportTicket(List<TicketChain> ticketchain) {
        this.ticketchain = ticketchain;
    }
    
    public void addTicket(TicketChain ticket) {
        ticketchain.add(ticket);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<TicketChain> getTicketchain() {
        return ticketchain;
    }

    public void setTicketchain(List<TicketChain> ticketchain) {
        this.ticketchain = ticketchain;
    }

    public Boolean getTicketOpen() {
        return ticketOpen;
    }

    public void setTicketOpen(Boolean ticketOpen) {
        this.ticketOpen = ticketOpen;
    }
    
    

    @Override
    public String toString() {
        return "SupportTicket{" + "id=" + id + ", ticketchain=" + ticketchain + '}';
    }
    
    

    
}

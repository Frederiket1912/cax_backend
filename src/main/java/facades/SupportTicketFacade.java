package facades;

import dtos.supportticketdto.GetSupportTicketDTO;
import dtos.supportticketdto.ReplySupportTicketDTO;
import dtos.supportticketdto.SupportTicketDTO;
import entities.SupportTicket;
import entities.TicketChain;
import entities.User;
import errorhandling.AlreadyExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import errorhandling.AuthenticationException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;


public class SupportTicketFacade {
  
    private static EntityManagerFactory emf;
    private static SupportTicketFacade instance;
    
    private SupportTicketFacade(){}
    
    /**
     * 
     * @param _emf
     * @return the instance of this facade.
     */
    public static SupportTicketFacade getTicketFacade (EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new SupportTicketFacade();
        }
        return instance;
    }
    
    public User createSupportTicket(SupportTicketDTO supticket) {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, supticket.getUsername());
            user.addTicket(supticket.getSupporttickets().get(0));
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return user;
    }
    
    public SupportTicket replySupportTicket(ReplySupportTicketDTO supticket) {
        EntityManager em = emf.createEntityManager();
        SupportTicket supportpticket;
        try {
            supportpticket = em.find(SupportTicket.class, supticket.getSupportid());
            supportpticket.addTicket(supticket.getSupporttickets().get(0));
            em.getTransaction().begin();
            em.persist(supportpticket);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return supportpticket;
    }
    
    public SupportTicket closeSupportTicket(int ticketID) {
        EntityManager em = emf.createEntityManager();
        SupportTicket ticket;
        try {
            ticket = em.find(SupportTicket.class, ticketID);
            ticket.setTicketOpen(Boolean.FALSE);
            em.getTransaction().begin();
            em.persist(ticket);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return ticket;
    }
    
    public GetSupportTicketDTO getSupportTicketUser(String userName) {
        EntityManager em = emf.createEntityManager();
        User user;
        GetSupportTicketDTO responsedto = new GetSupportTicketDTO();
        try {
                    user = em.find(User.class, userName);
                    SupportTicket response = new SupportTicket();
                    List<SupportTicket> supportticket = user.getSupportticket();
                    for (SupportTicket supportTicket : supportticket) {
                if (supportTicket.getTicketOpen() == true) {
                    response = supportTicket;
                }
                
                responsedto = new GetSupportTicketDTO(response);
            }
        } finally {
            em.close();
        }
        return responsedto;
    }
    
    public List<GetSupportTicketDTO> getOpenSupportTickets() {
        EntityManager em = emf.createEntityManager();
        List<GetSupportTicketDTO> responsedto = new ArrayList();
        try {
                    TypedQuery<SupportTicket> supticket = em.createQuery("SELECT s FROM SupportTicket s WHERE "
                    + "s.ticketOpen = :ticketOpen", SupportTicket.class)
                            .setParameter("ticketOpen", Boolean.TRUE);
                    List<SupportTicket> response = supticket.getResultList();
                    for (SupportTicket supportTicket : response) {
                responsedto.add(new GetSupportTicketDTO(supportTicket));
            }
                
            } finally {
            em.close();
        }
        return responsedto;
    }
    

     public static void main(String[] args) throws AlreadyExistsException {
         emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
        SupportTicketFacade UF = new SupportTicketFacade();
        /*SupportTicketDTO dto = new SupportTicketDTO();
        dto.setUsername("user");
        ArrayList<SupportTicket> support = new ArrayList();
        ArrayList<TicketChain> chain = new ArrayList();
        chain.add(new TicketChain("Need help with order #155151", "my order says cancelled but I haven't canceled the order", dto.getUsername()));
        support.add(new SupportTicket(chain));
        dto.setSupporttickets(support);
        System.out.println(UF.createSupportTicket(dto));*/
        
        
        //System.out.println(UF.closeSupportTicket(1));
        
        
        /*
        ReplySupportTicketDTO dto = new ReplySupportTicketDTO();
        dto.setSupportid(1);
        List<TicketChain> supporttickets = new ArrayList();
        supporttickets.add(new TicketChain("answer : Need help with order #155151", "Ok, I can see this looked like an error on our part. It should be fixed now", "admin"));
        dto.setSupporttickets(supporttickets);
         System.out.println(UF.replySupportTicket(dto));
        
        
        /*ReplySupportTicketDTO dto = new ReplySupportTicketDTO();
        dto.setSupportid(1);
        List<TicketChain> supporttickets = new ArrayList();
        supporttickets.add(new TicketChain("last test", "Ok, I"));
        dto.setSupporttickets(supporttickets);
         System.out.println(UF.replySupportTicket(dto));*/
        
         //System.out.println(UF.getSupportTicketUser("user").getTicketchain().get(1));
         
         
        
        
         //System.out.println(UF.getSupportTicketUser("user").get(0));
         
         
         
         System.out.println(UF.getOpenSupportTickets());
    }
}

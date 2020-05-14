package facades;

import dtos.orderdto.CheckOrderDTO;
import dtos.orderdto.CreateOrderDTO;
import dtos.supportticketdto.GetSupportTicketDTO;
import dtos.supportticketdto.ReplySupportTicketDTO;
import dtos.supportticketdto.SupportTicketDTO;
import entities.DiscountCode;
import entities.ListItem;
import entities.Order;
import entities.Role;
import entities.SupportTicket;
import entities.TicketChain;
import entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.EMF_Creator;


public class SupportTicketFacadeTest {
    private static EntityManagerFactory emf;
    private static SupportTicketFacade facade;
    private EntityManager em;
    
    private User u1, u2;
    private Role r1, r2;
    private ListItem l1, l2, l3;
    private Order o1, o2, o3;
    private List<ListItem> listitems1 = new ArrayList();
    private List<ListItem> listitems2 = new ArrayList();
    private List<ListItem> listitems3 = new ArrayList();;
    
    
    public SupportTicketFacadeTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.DROP_AND_CREATE);
        facade = SupportTicketFacade.getTicketFacade(emf);
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("User.deleteAllRows").executeUpdate();
            em.createNamedQuery("Order.deleteAllRows").executeUpdate();
            em.createNamedQuery("ListItem.deleteAllRows").executeUpdate();
            em.createNamedQuery("Role.deleteAllRows").executeUpdate();
            em.createNamedQuery("DiscountCode.deleteAllRows").executeUpdate();
            
            DiscountCode dc1 = new DiscountCode("Default", 0, 1000);
            em.persist(dc1);
            

            u1 = new User("test1", "test1");
            u2 = new User("test2", "test2");
            
            r1 = new Role("user");
            r2 = new Role("admin");

            u1.addRole(r1);
            u2.addRole(r2);
            
            em.persist(r1);
            em.persist(r2);
            
            em.persist(u1);
            em.persist(u2);
            em.getTransaction().commit();
            
            em.getTransaction().begin();
            l1 = new ListItem("plane", "test1", "10-10-1000", "20-20-2000", 100, 2);
            l2 = new ListItem("hotel", "test2", "11-11-1000", "22-22-2000", 200, 3);
            l3 = new ListItem("hotel", "test3", "12-12-1000", "23-23-2000", 300, 4);
            
            //List<ListItem> listitems1 = new ArrayList<>();
            listitems1.add(l1);
            listitems1.add(l2);
            
            //List<ListItem> listitems2 = new ArrayList<>();
            listitems2.add(l3);
            
            //List<ListItem> listitems3 = new ArrayList<>();
            listitems3.add(l3);
            listitems3.add(l2);
            
            o1 = new Order(listitems1);
            o2 = new Order(listitems2);
            o3 = new Order(listitems3);
            
            o1.setDiscountCode(dc1);
            o2.setDiscountCode(dc1);
            o3.setDiscountCode(dc1);
            
            u1 = em.find(User.class, u1.getUserName());
            u2 = em.find(User.class, u2.getUserName());
            
            u1.addOrder(o1);
            u1.addOrder(o2);
            u2.addOrder(o3);
            
            List<SupportTicket> t1list = new ArrayList();
            SupportTicket t1 = new SupportTicket();
            List<TicketChain> tc1 = new ArrayList();
            tc1.add(new TicketChain("subject", "comment", "test1"));
            t1.setTicketchain(tc1);
            t1list.add(t1);
            u1.addTicket(t1);
            
            em.persist(u1);
            em.persist(u2);
            em.getTransaction().commit();
            
        } finally {
            em.close();
        }
    }
    
    @AfterEach
    public void tearDown() {
    }


    /**
     * Test of createOrder method, of class OrderFacade.
     */
    @Test
    public void createSupportTicket() {
        SupportTicketDTO dto = new SupportTicketDTO();
        dto.setUsername("test1");
        ArrayList<SupportTicket> support = new ArrayList();
        ArrayList<TicketChain> chain = new ArrayList();
        chain.add(new TicketChain("Need help with order #155151", "my order says cancelled but I haven't canceled the order", dto.getUsername()));
        support.add(new SupportTicket(chain));
        dto.setSupporttickets(support);
        
        User user = facade.createSupportTicket(dto);
        
        assertNotNull(user.getSupportticket().get(0));
    }


    @Test
    public void replySupportTicket() {
        createSupportTicket();
        
        ReplySupportTicketDTO dto = new ReplySupportTicketDTO();
        dto.setSupportid(1);
        List<TicketChain> supporttickets = new ArrayList();
        supporttickets.add(new TicketChain("answer : Need help with order #155151", "Ok, I can see this looked like an error on our part. It should be fixed now", "admin"));
        dto.setSupporttickets(supporttickets);
        SupportTicket reply = facade.replySupportTicket(dto);
        
        assertNotNull(reply.getTicketchain().get(0));
    }
    
 
    @Test
    public void closeSupportTicket() {
        createSupportTicket();
        
        SupportTicket ticket = facade.closeSupportTicket(u1.getSupportticket().get(0).getId());
        
        assertEquals(ticket.getTicketOpen(), false);
    }
    
    @Test
    public void getSupportTicketUser() {
        createSupportTicket();
        
        GetSupportTicketDTO dto = facade.getSupportTicketUser("test1");
        assertNotNull(dto);
    }
    
    @Test
    public void getOpenSupportTickets() {
        createSupportTicket();
        
        List<GetSupportTicketDTO> ticketList = facade.getOpenSupportTickets();
        
        assertNotNull(ticketList);
    }


}

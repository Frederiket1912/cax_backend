package facades;

import dtos.orderdto.CheckOrderDTO;
import dtos.orderdto.CreateOrderDTO;
import entities.DiscountCode;
import entities.ListItem;
import entities.Order;
import entities.Role;
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


public class OrderFacadeTest {
    private static EntityManagerFactory emf;
    private static OrderFacade facade;
    private EntityManager em;
    
    private User u1, u2;
    private Role r1, r2;
    private ListItem l1, l2, l3;
    private Order o1, o2, o3;
    private List<ListItem> listitems1 = new ArrayList();
    private List<ListItem> listitems2 = new ArrayList();
    private List<ListItem> listitems3 = new ArrayList();;
    
    
    public OrderFacadeTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.DROP_AND_CREATE);
        facade = OrderFacade.getOrderFacade(emf);
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
    public void testCreateOrder() throws Exception {
        CreateOrderDTO orderDTO = new CreateOrderDTO();
        orderDTO.setUsername(u1.getUserName());
        orderDTO.setListItems(listitems1);
        assertEquals(listitems1, facade.createOrder(orderDTO).getListItems());
        assertEquals(u1.getUserName(), facade.createOrder(orderDTO).getUsername());
    }

    /**
     * Test of getOrdersFromUser method, of class OrderFacade.
     */
    @Test
    public void testGetOrdersFromUser() {
        assertEquals(2, facade.getOrdersFromUser(u1.getUserName()).size());
    }
    
    /**
     * Test of getAllOrders method, of class OrderFacade.
     */
    @Test
    public void testGetAllOrders() {
        assertEquals(3, facade.getAllOrders().size());
    }


    /**
     * Test of deleteOrder method, of class OrderFacade.
     */
    @Test
    public void testDeleteOrder() {
        Order order1 = facade.deleteOrder("1");
        Order order2 = facade.deleteOrder("2");
       Order order3 = facade.deleteOrder("3");
        assertEquals(true, order1.getCancelled());
    }


}

package facades;

import dtos.orderdto.CreateOrderDTO;
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
            em.createNamedQuery("ListItem.deleteAllRows").executeUpdate();
            em.createNamedQuery("Order.deleteAllRows").executeUpdate();
            em.createNamedQuery("Role.deleteAllRows").executeUpdate();
            em.createNamedQuery("User.deleteAllRows").executeUpdate();

            u1 = new User("test1", "test1");
            u2 = new User("test2", "test2");
            
            r1 = new Role("user");
            r2 = new Role("admin");

            l1 = new ListItem("plane", "test1", "10-10-1000", "20-20-2000", 100, 2);
            l2 = new ListItem("hotel", "test2", "11-11-1000", "22-22-2000", 200, 3);
            l2 = new ListItem("hotel", "test3", "12-12-1000", "23-23-2000", 300, 4);
            
            List<ListItem> listitems1 = new ArrayList<>();
            listitems1.add(l1);
            listitems1.add(l2);
            
            List<ListItem> listitems2 = new ArrayList<>();
            listitems1.add(l3);
            
            List<ListItem> listitems3 = new ArrayList<>();
            listitems1.add(l3);
            listitems1.add(l2);
            
            o1 = new Order(listitems1);
            o2 = new Order(listitems2);
            o3 = new Order(listitems3);
            
            u1.addRole(r1);
            u2.addRole(r2);
            
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
    //@Test
    public void testCreateOrder() throws Exception {
        System.out.println("createOrder");
        CreateOrderDTO orderDTO = null;
        OrderFacade instance = null;
        CreateOrderDTO expResult = null;
        CreateOrderDTO result = instance.createOrder(orderDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrdersFromUser method, of class OrderFacade.
     */
    //@Test
//    public void testGetOrdersFromUser() throws Exception {
//        assertEquals(2, facade.getOrdersFromUser(u1.getUserName()).size());
//    }
    
}

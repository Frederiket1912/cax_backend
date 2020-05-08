package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.listitemdto.ListItemDTO;
import dtos.orderdto.CheckOrderDTO;
import dtos.orderdto.CreateOrderDTO;
import entities.ListItem;
import entities.Order;
import entities.Role;
import entities.User;
import errorhandling.AlreadyExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import errorhandling.AuthenticationException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;


public class OrderFacade {
  
    private static EntityManagerFactory emf;
    private static OrderFacade instance;
    
    private OrderFacade(){}
    
    /**
     * 
     * @param _emf
     * @return the instance of this facade.
     */
    public static OrderFacade getOrderFacade (EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new OrderFacade();
        }
        return instance;
    }
    
    
    public CreateOrderDTO createOrder(CreateOrderDTO orderDTO) throws AlreadyExistsException {
        EntityManager em = emf.createEntityManager();
        Order order = new Order(orderDTO.getListItems());
        try {
            em.getTransaction().begin();
            
            User user = em.find(User.class, orderDTO.getUsername());
            user.addOrder(order);
            em.persist(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return orderDTO;
    }
    
    public List<CheckOrderDTO> getOrdersFromUser(String username){
         EntityManager em = emf.createEntityManager();
        try {          
            User u = em.find(User.class, username);
            List<Order> orders = u.getOrders();

            List<CheckOrderDTO> result = new ArrayList<>();
            
            for (Order o : orders) {
                result.add(new CheckOrderDTO(username, o));
            }
            return result;
        } finally {
            em.close();
        }
    }
    
    public List<CheckOrderDTO> getAllOrders() {
        EntityManager em = emf.createEntityManager();
        try {          
            TypedQuery<User> q = em.createQuery("SELECT u FROM User u", User.class);
            List<User> users = q.getResultList();
            List<CheckOrderDTO> result = new ArrayList<>();
            for (User u : users) {
                for (Order o : u.getOrders()) {
                    result.add(new CheckOrderDTO(u.getUserName(), o));
                }
            }
            return result;
        } finally {
            em.close();
        }
    }

    public Order deleteOrder(String id) {
        EntityManager em = emf.createEntityManager();
        Order order = new Order();
        try {
            em.getTransaction().begin();
            order = em.find(Order.class, Integer.parseInt(id));
            order.setCancelled(Boolean.TRUE);
            
            em.persist(order);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return order;
    }
    
         public static void main(String[] args) throws AlreadyExistsException {
         /*CreateOrderDTO orderDTO = new CreateOrderDTO();
         ArrayList<ListItem> list = new ArrayList();
         String username = "username";
         orderDTO.setListItems(list);
         orderDTO.setUsername(username);
         Gson gson = new GsonBuilder().setPrettyPrinting().create();
         
         System.out.println(gson.toJson(orderDTO));*/
         emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
         OrderFacade of = new OrderFacade();
         
             System.out.println(of.deleteOrder("1"));
    }
}

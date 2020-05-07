package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
    public static OrderFacade getUserFacade (EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new OrderFacade();
        }
        return instance;
    }
    
    public User getVeryfiedUser(String username, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
            if (user == null || !user.verifyPassword(password)) {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally {
            em.close();
        }
        return user;
    }
    
    public CreateOrderDTO createOrder(CreateOrderDTO orderDTO) throws AlreadyExistsException {
        EntityManager em = emf.createEntityManager();
        Order order = new Order(orderDTO.getListItems());
            //User user = new User();
            //user.setUserName(orderDTO.getUsername());
            //user.addOrder(order);
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
    


     public static void main(String[] args) throws AlreadyExistsException {
         CreateOrderDTO orderDTO = new CreateOrderDTO();
         ArrayList<ListItem> list = new ArrayList();
         String username = "username";
         orderDTO.setListItems(list);
         orderDTO.setUsername(username);
         Gson gson = new GsonBuilder().setPrettyPrinting().create();
         
         System.out.println(gson.toJson(orderDTO));
    }
}

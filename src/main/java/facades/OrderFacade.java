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
                List<ListItemDTO> listdto = new ArrayList<>();
                for (ListItem li : o.getListitems()) {
                    listdto.add(new ListItemDTO(li));
                }
                result.add(new CheckOrderDTO(username, listdto));
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
            List<ListItemDTO> listdto = new ArrayList<>();
            for (User u : users) {
                for (Order o : u.getOrders()) {
                    for (ListItem li : o.getListitems()) {
                    listdto.add(new ListItemDTO(li));                   
                }
                    result.add(new CheckOrderDTO(u.getUserName(), listdto));
                }
            }
            return result;
        } finally {
            em.close();
        }
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

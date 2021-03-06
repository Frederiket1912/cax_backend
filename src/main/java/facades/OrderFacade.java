package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.listitemdto.ListItemDTO;
import dtos.orderdto.CheckOrderDTO;
import dtos.orderdto.CreateOrderDTO;
import dtos.orderdto.DiscountCodeDTO;
import entities.DiscountCode;
import entities.ListItem;
import entities.Order;
import entities.Role;
import entities.User;
import errorhandling.AlreadyExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import errorhandling.AuthenticationException;
import errorhandling.NotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

public class OrderFacade {

    private static EntityManagerFactory emf;
    private static OrderFacade instance;

    private OrderFacade() {
    }

    /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static OrderFacade getOrderFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new OrderFacade();
        }
        return instance;
    }

    /**
     * This method is used to create new orders.
     * @param orderDTO
     * @return CreateOrderDTO A DTO of the created order.
     * @throws AlreadyExistsException
     */
    public CreateOrderDTO createOrder(CreateOrderDTO orderDTO) throws AlreadyExistsException {
        EntityManager em = emf.createEntityManager();
        Order order = new Order(orderDTO.getListItems());

        try {
            em.getTransaction().begin();
            if (null != orderDTO.getDiscountCode()) {
                TypedQuery<DiscountCode> q = em.createQuery("SELECT d FROM DiscountCode d WHERE "
                        + "d.code = :code", DiscountCode.class)
                        .setParameter("code", orderDTO.getDiscountCode().getCode());

                DiscountCode dc = q.getSingleResult();
                order.setDiscountCode(dc);
            }
            User user = em.find(User.class, orderDTO.getUsername());
            user.addOrder(order);
            em.persist(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return orderDTO;
    }

    /**
     * This method is used to get a full list of all orders from one user.
     * @param username
     * @return List<CheckOrderDTO> A list of all the users orders as CheckOrderDTO's. 
     */
    public List<CheckOrderDTO> getOrdersFromUser(String username) {
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

    /**
     * This method is used to get a list of all orders in the DB.
     * @return List<CheckOrderDTO> A list of all orders from all users as CheckOrderDTO's.
     */
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

    /**
     * This method is used to mark an order as cancelled.
     * @param id
     * @return Order The cancelled order.
     */
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

    /**
     * This method is used to create new discount codes.
     * @param name
     * @param discountPercentage
     * @param code
     * @return DiscountCodeDTO a DTO of the created DiscountCode.
     * @throws AlreadyExistsException
     */
    public DiscountCodeDTO createDiscountCode(String name, int discountPercentage, int code) throws AlreadyExistsException {
        EntityManager em = emf.createEntityManager();
        DiscountCode dc = new DiscountCode(name, discountPercentage, code);
        try {
            em.getTransaction().begin();
            em.persist(dc);
            em.getTransaction().commit();
        } catch (RollbackException ex) {
            throw new AlreadyExistsException("Discount code with given code already exists");
        } finally {
            em.close();
        }
        return new DiscountCodeDTO(dc);
    }

    /**
     * This method is used to find a DiscountCode from it's id.
     * @param id
     * @return DiscountCodeDTO a DTO of the found DiscountCode.
     * @throws NotFoundException
     */
    public DiscountCodeDTO getDiscountCodeByID(int id) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        try {
            DiscountCode dc = em.find(DiscountCode.class, id);
            if (null == dc) {
                throw new NotFoundException("Discount code with given id could not be found.");
            }
            return new DiscountCodeDTO(dc);
        } finally {
            em.close();
        }
    }

    /**
     * This method is used to find a DiscountCode from it's code.
     * @param code
     * @return DiscountCodeDTO a DTO of the found DiscountCode.
     * @throws NotFoundException
     */
    public DiscountCodeDTO getDiscountCodeByCode(int code) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<DiscountCode> q = em.createQuery("SELECT d FROM DiscountCode d WHERE "
                    + "d.code = :code", DiscountCode.class)
                    .setParameter("code", code);

            DiscountCode dc = q.getSingleResult();
            DiscountCodeDTO result = new DiscountCodeDTO(dc);
            return result;
        } catch (NoResultException ex) {
            throw new NotFoundException("No discount code with give code found.");
        } finally {
            em.close();
        }
    }

    /**
     * This method is used to get all DiscountCode's in the DB.
     * @return List<DiscountCodeDTO> A list of all DiscountCode's in the DB.
     */
    public List<DiscountCodeDTO> getAllDiscountCodes() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<DiscountCode> q = em.createQuery("SELECT d FROM DiscountCode d", DiscountCode.class);

            List<DiscountCode> dcs = q.getResultList();
            List<DiscountCodeDTO> result = new ArrayList<>();
            for (DiscountCode dc : dcs) {
                result.add(new DiscountCodeDTO(dc));
            }
            return result;
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) throws AlreadyExistsException, NotFoundException {
        /*CreateOrderDTO orderDTO = new CreateOrderDTO();
         ArrayList<ListItem> list = new ArrayList();
         String username = "username";
         orderDTO.setListItems(list);
         orderDTO.setUsername(username);
         Gson gson = new GsonBuilder().setPrettyPrinting().create();
         
         System.out.println(gson.toJson(orderDTO));*/
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
        OrderFacade of = new OrderFacade();

        System.out.println(of.getAllDiscountCodes());
    }
}

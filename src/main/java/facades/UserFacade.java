package facades;

import entities.Role;
import entities.User;
import errorhandling.AlreadyExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import errorhandling.AuthenticationException;
import utils.EMF_Creator;


public class UserFacade {
  
    private static EntityManagerFactory emf;
    private static UserFacade instance;
    
    private UserFacade(){}
    
    /**
     * 
     * @param _emf
     * @return the instance of this facade.
     */
    public static UserFacade getUserFacade (EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserFacade();
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
    
    public User createNormalUser(String username, String password) throws AlreadyExistsException {
        EntityManager em = emf.createEntityManager();
        User userregister = new User(username, password);
        Role userRole = new Role("user");
        userregister.addRole(userRole);
        try {
            User user = em.find(User.class, username);
            if (user != null ) {
                throw new AlreadyExistsException("User name already exists");
            }
            em.getTransaction().begin();
            em.persist(userregister);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return userregister;
    }
    
    public User createAdminUser(String username, String password) throws AlreadyExistsException {
        EntityManager em = emf.createEntityManager();
        User userregister = new User(username, password);
        Role userRole = new Role("admin");
        userregister.addRole(userRole);
        try {
            User user = em.find(User.class, username);
            if (user != null ) {
                throw new AlreadyExistsException("User name already exists");
            }
            em.getTransaction().begin();
            em.persist(userregister);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return userregister;
    }
    
    public User createSupportUser(String username, String password) throws AlreadyExistsException {
        EntityManager em = emf.createEntityManager();
        User userregister = new User(username, password);
        Role userAdmin = new Role("user");
        Role userUser = new Role("admin");
        userregister.addRole(userAdmin);
        userregister.addRole(userUser);
        try {
            User user = em.find(User.class, username);
            if (user != null ) {
                throw new AlreadyExistsException("User name already exists");
            }
            em.getTransaction().begin();
            em.persist(userregister);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return userregister;
    }

     public static void main(String[] args) throws AlreadyExistsException {
         emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
        UserFacade UF = new UserFacade();
         System.out.println(UF.createSupportUser("testsupport", "test2020"));
    }
}

package utils;


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

public class SetupTestUsers {

  public static void main(String[] args) {

    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    EntityManager em = emf.createEntityManager();
    
    // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
    // CHANGE the three passwords below, before you uncomment and execute the code below
    // Also, either delete this file, when users are created or rename and add to .gitignore
    // Whatever you do DO NOT COMMIT and PUSH with the real passwords

    User user = new User("", "");
    User admin = new User("", "");
    User both = new User("", "");
    User support = new User("", "");

    if(admin.getUserPass().equals("test")||user.getUserPass().equals("test")||both.getUserPass().equals("test"))
      throw new UnsupportedOperationException("You have not changed the passwords");
    
    ArrayList<ListItem> order = new ArrayList<>();
         order.add(new ListItem("Plane","London-Paris", "2020-05-15", "2020-05-25", 100, 2));
         order.add(new ListItem("Plane","London-Paris", "02020-05-15", "2020-05-25", 100, 2));
         order.add(new ListItem("Plane","London-Paris", "2020-05-15", "2020-05-25", 100, 2));
         order.add(new ListItem("Plane","London-Paris", "2020-05-15", "2020-05-25", 100, 2));
         order.add(new ListItem("Plane","London-Paris", "2020-05-15", "2020-05-25", 100, 2));
         
         Order order2 = new Order(order);
         Order order3 = new Order(order);
         
    user.addOrder(order2);
    user.addOrder(order3);
    
    DiscountCode dc = new DiscountCode("20 percent", 20, 2222);
    DiscountCode dc2 = new DiscountCode("10 percent", 10, 1111);
    order2.setDiscountCode(dc);
    order3.setDiscountCode(dc2);
    
    /*SupportTicket supportticket = new SupportTicket();
    List<TicketChain> ticketchain = new ArrayList();
    ticketchain.add(new TicketChain("subject1", "comment1", "user"));
    ticketchain.add(new TicketChain("subject2", "comment2", "user"));
    ticketchain.add(new TicketChain("subject3", "comment3", "user"));
    supportticket.setTicketchain(ticketchain);*/
    

    em.getTransaction().begin();
    em.persist(dc);
    em.persist(dc2);
    Role userRole = new Role("user");
    Role adminRole = new Role("admin");
    Role supportRole = new Role("support");
    user.addRole(userRole);
    admin.addRole(adminRole);
    support.addRole(supportRole);
    both.addRole(userRole);
    both.addRole(adminRole);
    em.persist(userRole);
    em.persist(adminRole);
    em.persist(supportRole);
    em.persist(user);
    em.persist(admin);
    em.persist(both);
    em.persist(support);
    em.getTransaction().commit();
    System.out.println("PW: " + user.getUserPass());
    System.out.println("Testing user with OK password: " + user.verifyPassword("test"));
    System.out.println("Testing user with wrong password: " + user.verifyPassword("test1"));
    System.out.println("Created TEST Users");
   
  }


}

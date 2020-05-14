package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.supportticketdto.GetSupportTicketDTO;
import dtos.supportticketdto.ReplySupportTicketDTO;
import dtos.supportticketdto.SupportTicketDTO;
import entities.Role;
import entities.SupportTicket;
import entities.TicketChain;
import entities.User;
import facades.SupportTicketFacade;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.with;
import io.restassured.parsing.Parser;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;


public class SupportTicketResourceTest {
    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api/";
    private EntityManager em;
    
    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    private static User u1 = new User("user1", "test1");

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.DROP_AND_CREATE);
        
        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
        
        //Create 2 dummy users
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("SupportTicket.deleteAllRows").executeUpdate();
            em.createNamedQuery("TicketChain.deleteAllRows").executeUpdate();
            em.createNamedQuery("User.deleteAllRows").executeUpdate();
            em.createNamedQuery("Order.deleteAllRows").executeUpdate();
            em.createNamedQuery("ListItem.deleteAllRows").executeUpdate();
            em.createNamedQuery("Role.deleteAllRows").executeUpdate();

            //User u1 = new User("user1", "test1");
            User u2 = new User("user2", "test2");
            
            Role r1 = new Role("user");
            Role r2 = new Role("admin");

            u1.addRole(r1);
            u2.addRole(r2);
                        
            em.persist(r1);
            em.persist(r2);
            
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
    
    @AfterAll
    public static void closeTestServer(){
        //System.in.read();
         //Don't forget this, if you called its counterpart in @BeforeAll
         EMF_Creator.endREST_TestWithDB();
         httpServer.shutdownNow();
    }
    
    @Test
    public void createSupportTicket() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        SupportTicketDTO dto = new SupportTicketDTO();
        dto.setUsername("user1");
        ArrayList<SupportTicket> support = new ArrayList();
        ArrayList<TicketChain> chain = new ArrayList();
        chain.add(new TicketChain("Need help with order #155151", "my order says cancelled but I haven't canceled the order", dto.getUsername()));
        support.add(new SupportTicket(chain));
        dto.setSupporttickets(support);
        
         

        SupportTicketDTO result
                = with()
                        .body(gson.toJson(dto)) //include object in body
                        .contentType("application/json")
                        .when().request("POST", "ticket/create").then() //post REQUEST
                        .assertThat()
                        .statusCode(HttpStatus.OK_200.getStatusCode())
                        .extract()
                        .as(SupportTicketDTO.class); //extract result JSON as object

        assertNotNull(result.getSupporttickets().get(0));
    }
    
    @Test
    public void replySupportTicket() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        ReplySupportTicketDTO dto = new ReplySupportTicketDTO();
        dto.setSupportid(u1.getSupportticket().get(0).getId());
        List<TicketChain> supporttickets = new ArrayList();
        supporttickets.add(new TicketChain("answer : Need help with order #155151", "Ok, I can see this looked like an error on our part. It should be fixed now", "admin"));
        dto.setSupporttickets(supporttickets);
        
         

        SupportTicket result
                = with()
                        .body(gson.toJson(dto)) //include object in body
                        .contentType("application/json")
                        .when().request("POST", "ticket/reply").then() //post REQUEST
                        .assertThat()
                        .statusCode(HttpStatus.OK_200.getStatusCode())
                        .extract()
                        .as(SupportTicket.class); //extract result JSON as object

        assertNotNull(result.getId());
    }
    
    @Test
    public void closeSupportTicket() {
        SupportTicketDTO dto = new SupportTicketDTO();
        dto.setUsername("test1");
        ArrayList<SupportTicket> support = new ArrayList();
        ArrayList<TicketChain> chain = new ArrayList();
        chain.add(new TicketChain("Need help with order #155151", "my order says cancelled but I haven't canceled the order", dto.getUsername()));
        support.add(new SupportTicket(chain));
        dto.setSupporttickets(support);
        String ticketId = String.valueOf(u1.getSupportticket().get(0).getId());

        SupportTicket result
                = with()
                        .contentType("application/json")
                        .when().request("PUT", "ticket/close/"+ticketId).then() 
                        .assertThat()
                        .statusCode(HttpStatus.OK_200.getStatusCode())
                        .extract()
                        .as(SupportTicket.class); //extract result JSON as object

        assertEquals(false, result.getTicketOpen());
    }
    
    //Works
    @Test
    public void getSupportTicketUser() {
        GetSupportTicketDTO result
                = with()
                        .contentType("application/json")
                        .when().request("GET", "ticket/"+u1.getUserName()).then() 
                        .assertThat()
                        .statusCode(HttpStatus.OK_200.getStatusCode())
                        .extract()
                        .as(GetSupportTicketDTO.class); //extract result JSON as object

        assertNotNull(result);
    }
    
    //Works
    @Test
    public void getOpenSupportTickets() {
        GetSupportTicketDTO[] result
                = with()
                        .contentType("application/json")
                        .when().request("GET", "ticket/open").then() 
                        .assertThat()
                        .statusCode(HttpStatus.OK_200.getStatusCode())
                        .extract()
                        .as(GetSupportTicketDTO[].class); //extract result JSON as object

        assertNotNull(result);
        assertEquals(u1.getSupportticket().get(0).getTicketchain().get(0).getSubject(), result[0].getTicketchain().get(0).getSubject());
    }
    
    
       
}

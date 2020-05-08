package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.hoteldto.HotelDTO;
import dtos.hoteldto.HotelSearchDTO;
import dtos.orderdto.CreateOrderDTO;
import dtos.planedto.CarriersDTO;
import dtos.planedto.FlightDTO;
import entities.ListItem;
import entities.Order;
import entities.Role;
import entities.User;
import facades.OrderFacade;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import io.restassured.parsing.Parser;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import jdk.nashorn.internal.ir.annotations.Ignore;
import net.minidev.json.JSONObject;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;


public class OrderResourceTest {
    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api/";
    private EntityManager em;
    
    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.DROP_AND_CREATE);
        OrderFacade facade = OrderFacade.getOrderFacade(emf);
        
        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
        
        //Create 2 dummy users
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("User.deleteAllRows").executeUpdate();
            em.createNamedQuery("Order.deleteAllRows").executeUpdate();
            em.createNamedQuery("ListItem.deleteAllRows").executeUpdate();
            em.createNamedQuery("Role.deleteAllRows").executeUpdate();          

            User u1 = new User("user1", "test1");
            User u2 = new User("user2", "test2");
            
            Role r1 = new Role("user");
            Role r2 = new Role("admin");

            u1.addRole(r1);
            u2.addRole(r2);
            
            em.persist(r1);
            em.persist(r2);
            
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
    public void testCreateOrder() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        CreateOrderDTO cOrderDTO = new CreateOrderDTO();
        List<ListItem> listitems = new ArrayList();
        listitems.add(new ListItem("flight", "Paris-London", "2020-05-08", "2020-08-18", 2525, 15));
        listitems.add(new ListItem("flight", "France-Germany", "2020-05-08", "2020-08-18", 2525, 15));
        listitems.add(new ListItem("hotel", "The Grand NYC", "2020-05-08", "2020-08-18", 2525, 15));
        listitems.add(new ListItem("hotel", "Bowery Grand Hotel", "2020-05-08", "2020-08-18", 2525, 15));
        cOrderDTO.setUsername("user1");
        cOrderDTO.setListItems(listitems);
         

        CreateOrderDTO result
                = with()
                        .body(gson.toJson(cOrderDTO)) //include object in body
                        .contentType("application/json")
                        .when().request("POST", "/order/create").then() //post REQUEST
                        .assertThat()
                        .statusCode(HttpStatus.OK_200.getStatusCode())
                        .extract()
                        .as(CreateOrderDTO.class); //extract result JSON as object

        //checking that nothing has changed that we don't want to change
        System.out.println(result.getListItems().get(1).getName());
        assertEquals("France-Germany", result.getListItems().get(1).getName());
        assertNotNull(result);
    }
    
    
       
}

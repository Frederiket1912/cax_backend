package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.orderdto.CreateOrderDTO;
import entities.ListItem;
import entities.Order;
import entities.User;
import errorhandling.AlreadyExistsException;
import facades.OrderFacade;
import java.util.ArrayList;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import utils.EMF_Creator;



@Path("order")
public class OrderResource {

    private static EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final OrderFacade FACADE = OrderFacade.getOrderFacade(EMF);

    

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String createOrder(CreateOrderDTO order) {
       try {
            return GSON.toJson(FACADE.createOrder(order));
        } catch (AlreadyExistsException ex) {
            throw new WebApplicationException(ex.getMessage(), 400);
        }
    }
    

    @GET
    @Path("get/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getOrdersFromUser(@PathParam("username") String username){
        return GSON.toJson(FACADE.getOrdersFromUser(username));
    }
    
    @GET
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllOrders(){
        return GSON.toJson(FACADE.getAllOrders());
    }

    @DELETE
    @Path("/delete/{orderid}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteOrder(@PathParam("orderid") String orderid) {
            return GSON.toJson(FACADE.deleteOrder(orderid));

    }


    
}

package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.orderdto.CreateOrderDTO;
import dtos.orderdto.DiscountCodeDTO;
import entities.ListItem;
import entities.Order;
import entities.User;
import errorhandling.AlreadyExistsException;
import errorhandling.NotFoundException;
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

    /**
     * This endpoint is used to create new orders.
     * @param order
     * @return JSON object corresponding to a CreateOrderDTO of the created order.
     */
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

    /**
     * This endpoint is used to get all orders from a given user.
     * @param username
     * @return JSON object corresponding to a list of all orders from the user.
     */
    @GET
    @Path("get/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getOrdersFromUser(@PathParam("username") String username) {
        return GSON.toJson(FACADE.getOrdersFromUser(username));
    }

    /**
     * This endpoint is used to get a list of all orders in the DB.
     * @return JSON object corresponding to a list of all orders.
     */
    @GET
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllOrders() {
        return GSON.toJson(FACADE.getAllOrders());
    }

    /**
     * This endpoint is used to set an orders status to cancelled. 
     * @param orderid
     * @return JSON object corresponding to the cancelled order.
     */
    @DELETE
    @Path("/delete/{orderid}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteOrder(@PathParam("orderid") String orderid) {
        return GSON.toJson(FACADE.deleteOrder(orderid));

    }

    /**
     * This endpoint is used to fetch a DiscountCode from it's id.
     * @param id
     * @return JSON object corresponding to a DiscountCode with the given id.
     */
    @GET
    @Path("get/discountcode/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDiscountCodeByID(@PathParam("id") int id) {
        try {
            return GSON.toJson(FACADE.getDiscountCodeByID(id));
        } catch (NotFoundException ex) {
            throw new WebApplicationException("No discount code with given id exists.", 404);
        }
    }

    /**
     * This endpoint is used to fetch a DiscountCode from it's code.
     * @param code
     * @return JSON object corresponding to a DiscountCode with the given code.
     */
    @GET
    @Path("get/discountcode/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDiscountCodeByCode(@PathParam("code") int code) {
        try {
            return GSON.toJson(FACADE.getDiscountCodeByCode(code));
        } catch (NotFoundException ex) {
            throw new WebApplicationException("No discount code with given code exists.", 404);
        }
    }

    /**
     * This endpoint is used to fetch all discount codes.
     * @return JSON object corresponding to a list of all discount codes.
     */
    @GET
    @Path("get/discountcode/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllDiscountCodes() {
        return GSON.toJson(FACADE.getAllDiscountCodes());
    }

    /**
     * This endpoint is used to post a new DiscountCode.
     * @param discountcode
     * @return JSON object corresponding to the created DiscountCode.
     * @throws AlreadyExistsException
     */
    @POST
    @Path("create/discountcode")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String createDiscountCode(DiscountCodeDTO discountcode) throws AlreadyExistsException {
        try {
            return GSON.toJson(FACADE.createDiscountCode(discountcode.getName(), discountcode.getDiscountPercentage(), discountcode.getCode()));
        } catch (AlreadyExistsException ex) {
            throw new WebApplicationException("Discount code with given code already exists. New discount code could not be created.", 422);
        }
    }

}

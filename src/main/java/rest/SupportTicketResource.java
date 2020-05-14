package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.supportticketdto.ReplySupportTicketDTO;
import dtos.supportticketdto.SupportTicketDTO;
import entities.User;
import errorhandling.NotFoundException;
import facades.SupportTicketFacade;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

@Path("ticket")
public class SupportTicketResource {

    private static EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final SupportTicketFacade FACADE = SupportTicketFacade.getTicketFacade(EMF);

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    //@RolesAllowed("user")
    public String createSupportTicket(SupportTicketDTO ticket) {
        //User user = FACADE.createSupportTicket(ticket);
        //return GSON.toJson(user);
        //return "Ticket created";
        SupportTicketDTO created = FACADE.createSupportTicket(ticket);
        return GSON.toJson(created);
    }

    @POST
    @Path("/reply")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String replySupportTicket(ReplySupportTicketDTO supportticket) {
        return GSON.toJson(FACADE.replySupportTicket(supportticket));
    }

    @PUT
    @Path("/close/{ticketid}")
    @Produces(MediaType.APPLICATION_JSON)
    public String closeSupportTicket(@PathParam("ticketid") String ticketid) {
        return GSON.toJson(FACADE.closeSupportTicket(Integer.parseInt(ticketid)));
    }

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSupportTicketUser(@PathParam("username") String username) throws NotFoundException {
        try {
            return GSON.toJson(FACADE.getSupportTicketUser(username));
        } catch (NotFoundException ex) {
            throw new WebApplicationException("No support tickets open.", 404);
        }

    }

    @GET
    @Path("/open")
    @Produces(MediaType.APPLICATION_JSON)
    public String getOpenSupportTickets() {
        try {
            return GSON.toJson(FACADE.getOpenSupportTickets());
        } catch (NotFoundException ex) {
            throw new WebApplicationException("No open tickets.", 404);
        }
    }

}

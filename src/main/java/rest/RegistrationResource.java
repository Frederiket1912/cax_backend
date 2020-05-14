package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import errorhandling.AlreadyExistsException;
import facades.UserFacade;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import utils.EMF_Creator;



@Path("register")
public class RegistrationResource {

    private static EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final UserFacade FACADE = UserFacade.getUserFacade(EMF);

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String dummyMessage() {
        return "{\"msg\":\"Dummy message\"}";
    }

    @POST
    @Path("/user/{username}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public String createUser(@PathParam("username") String username, @PathParam("password") String password) {
        try {
            return GSON.toJson(FACADE.createNormalUser(username, password));
        } catch (AlreadyExistsException ex) {
            throw new WebApplicationException(ex.getMessage(), 400);
        }
    }

    @POST
    @Path("/admin/{username}/{password}/{role}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public String createAdmin(@PathParam("username") String username, @PathParam("password") String password, @PathParam("role") String role) {
        try {
            return GSON.toJson(FACADE.adminCreateUser(username, password, role));
        } catch (AlreadyExistsException ex) {
            throw new WebApplicationException(ex.getMessage(), 400);
        }
    }
    
    /**
     * This endpoint is used when a user wants to change his password.
     * @param jsonString
     * @return username of the user who changed password.
     */
    @PUT
    @Path("/changepw")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String changePassword(String jsonString) {
    JsonObject json = new JsonParser().parse(jsonString).getAsJsonObject();
    String username = json.get("username").getAsString();
    String newPassword = json.get("newPassword").getAsString();
    return GSON.toJson(FACADE.changeUserPW(username, newPassword).getUserName());
    }
        
}

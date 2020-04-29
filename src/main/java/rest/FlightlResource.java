package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.FlightApiMapper;
import java.io.IOException;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("flight")
public class FlightlResource {

    FlightApiMapper flightmapper = new FlightApiMapper();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    //TODO: Add params
    public String getAllHotels() throws InterruptedException, IOException {
        return gson.toJson(flightmapper.getListOfAvailPlanes());

    }

}

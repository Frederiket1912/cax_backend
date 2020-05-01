package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.planedto.FlightSearchDTO;
import facades.FlightApiMapper;
import java.io.IOException;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("flight")
public class FlightlResource {

    FlightApiMapper flightmapper = new FlightApiMapper();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @POST
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getSearchedFlights(FlightSearchDTO searchdata) throws InterruptedException, IOException {
        return gson.toJson(flightmapper.getListOfAvailPlanes(searchdata));
    }

}

package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.HotelApiMapper;
import dtos.hoteldto.HotelDTO;
import java.io.IOException;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("hotel")
public class HotelResource {

    HotelApiMapper hotelmapper = new HotelApiMapper();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    //If we want to show a next page we would need to get an integer param in.
    public String getAllHotels() throws InterruptedException, IOException {
        return gson.toJson(hotelmapper.getListOfHotels());

    }

}

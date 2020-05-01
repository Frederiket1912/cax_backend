package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.HotelApiMapper;
import dtos.hoteldto.HotelDTO;
import dtos.hoteldto.HotelSearchDTO;
import java.io.IOException;
import java.time.LocalDate;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import net.minidev.json.JSONObject;

@Path("hotel")
public class HotelResource {

    HotelApiMapper hotelmapper = new HotelApiMapper();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @POST
    @Path("/searchlist")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getSearchedHotel(HotelSearchDTO searchdata) throws InterruptedException, IOException {
        return gson.toJson(hotelmapper.getListOfHotels(searchdata));

    }
    
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllHotels() throws InterruptedException, IOException {
        LocalDate today = LocalDate.now();
        LocalDate plus7days = LocalDate.now().plusDays(7);
        HotelSearchDTO searchDTO = new HotelSearchDTO(today.toString(), plus7days.toString(), "1");
        return gson.toJson(hotelmapper.getListOfHotels(searchDTO));
    }
    
    
}

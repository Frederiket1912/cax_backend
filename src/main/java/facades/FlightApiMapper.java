package facades;

import dtos.planedto.FlightDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.hoteldto.HotelDTO;
import dtos.planedto.FlightSearchDTO;
import java.io.IOException;
import utils.HttpUtils;


public class FlightApiMapper {
    
    public FlightDTO getListOfAvailPlanes(FlightSearchDTO url) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String fetchdata = HttpUtils.fetchData(url.toString(), "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com");
        FlightDTO flightDTO = gson.fromJson(fetchdata, FlightDTO.class);
        return flightDTO;
    }
        
    
}

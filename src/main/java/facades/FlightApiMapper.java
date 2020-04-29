package facades;

import dtos.planedto.FlightDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.hoteldto.HotelDTO;
import java.io.IOException;
import utils.HttpUtils;


public class FlightApiMapper {
    
    public FlightDTO getListOfAvailPlanes() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String fetchdata = HttpUtils.fetchData("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browsedates/v1.0/US/USD/en-US/lond/pari/2020-05/2020-06", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com", "e0d467d76bmsh9ff99e16d60d6c1p11ec83jsn67c7df490a96");
        FlightDTO flightDTO = gson.fromJson(fetchdata, FlightDTO.class);
        return flightDTO;
    }
    
    
}

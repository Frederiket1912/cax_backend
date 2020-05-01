package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.hoteldto.HotelDTO;
import dtos.hoteldto.HotelSearchDTO;
import java.io.IOException;
import utils.HttpUtils;


public class HotelApiMapper {
    
    public HotelDTO getListOfHotels(HotelSearchDTO url) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String fetchdata = HttpUtils.fetchData(url.toString(), "hotels4.p.rapidapi.com");
        HotelDTO hotels = gson.fromJson(fetchdata, HotelDTO.class);
        return hotels;
    }
    

    
}

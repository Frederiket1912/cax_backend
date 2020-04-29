package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.hoteldto.HotelDTO;
import java.io.IOException;
import utils.HttpUtils;


public class HotelApiMapper {
    
    public HotelDTO getListOfHotels() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String fetchdata = HttpUtils.fetchData("https://hotels4.p.rapidapi.com/properties/list?currency=USD&locale=en_US&sortOrder=PRICE&destinationId=1506246&pageNumber=1&checkIn=2020-01-08&checkOut=2020-01-15&pageSize=25&adults1=1", "hotels4.p.rapidapi.com", "e0d467d76bmsh9ff99e16d60d6c1p11ec83jsn67c7df490a96");
        HotelDTO hotels = gson.fromJson(fetchdata, HotelDTO.class);
        return hotels;
    }
    
    public static void main(String[] args) throws IOException {
        HotelApiMapper hotel = new HotelApiMapper();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        HotelDTO output = hotel.getListOfHotels();
        System.out.println(gson.toJson(output));
    }
    
}

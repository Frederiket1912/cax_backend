
package dtos.hoteldto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import utils.HttpUtils;


public class HotelDTO {
    private DataDTO data;

    public HotelDTO() {
    }

    public HotelDTO(DataDTO data) {
        this.data = data;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TestDTO{" + "data=" + data + '}';
    }
    
    
    public static void main(String[] args) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String test = HttpUtils.fetchData("https://hotels4.p.rapidapi.com/properties/list?currency=USD&locale=en_US&sortOrder=PRICE&destinationId=1506246&pageNumber=1&checkIn=2020-01-08&checkOut=2020-01-15&pageSize=25&adults1=1", "hotels4.p.rapidapi.com", "e0d467d76bmsh9ff99e16d60d6c1p11ec83jsn67c7df490a96");
        HotelDTO testDTO = gson.fromJson(test, HotelDTO.class);
    }
    
}

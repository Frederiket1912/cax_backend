package dtos.hoteldto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.DTOInterface;
import java.io.IOException;
import java.util.ArrayList;
import utils.HttpUtils;


class DataDTO implements DTOInterface{
    BodyDTO body;
    private boolean failed;

    public DataDTO() {
    }

    public DataDTO(BodyDTO body) {
        this.body = body;
    }

    public BodyDTO getBody() {
        return body;
    }

    public void setBody(BodyDTO body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "DataDTO{" + "body=" + body + '}';
    }
    
    @Override
    public void fetch(){
        try{
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String data = HttpUtils.fetchData("hotels4.p.rapidapi.com", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com", "e0d467d76bmsh9ff99e16d60d6c1p11ec83jsn67c7df490a96");
        DataDTO dataDTO = gson.fromJson(data, DataDTO.class);
        body = dataDTO.body;
        }catch(IOException ex){
            this.failed=true;
        }
    }
    
    public static void main(String[] args) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String data = HttpUtils.fetchData("https://hotels4.p.rapidapi.com/properties/get-details?locale=en_US&currency=USD&checkOut=2020-01-15&adults1=1&checkIn=2020-01-08&id=424023", "hotels4.p.rapidapi.com", "e0d467d76bmsh9ff99e16d60d6c1p11ec83jsn67c7df490a96");
        //DataDTO dataDTO = gson.fromJson(data, DataDTO.class);
        HotelDTO dataDTO = gson.fromJson(data, HotelDTO.class);
        System.out.println(dataDTO);
    }
    
    
}

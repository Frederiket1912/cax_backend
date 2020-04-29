package dtos.planedto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.hoteldto.HotelDTO;
import java.io.IOException;
import java.util.ArrayList;
import utils.HttpUtils;


public class FlightDTO {
    DatesDTO Dates;
    ArrayList<QuotesDTO> Quotes;
    ArrayList<PlacesDTO> Places;
    ArrayList<CarriersDTO> Carriers;

    public FlightDTO() {
    }

    public FlightDTO(DatesDTO Dates, ArrayList<QuotesDTO> Quotes, ArrayList<PlacesDTO> Places, ArrayList<CarriersDTO> Carriers) {
        this.Dates = Dates;
        this.Quotes = Quotes;
        this.Places = Places;
        this.Carriers = Carriers;
    }

    public DatesDTO getDates() {
        return Dates;
    }

    public void setDates(DatesDTO Dates) {
        this.Dates = Dates;
    }

    public ArrayList<QuotesDTO> getQuotes() {
        return Quotes;
    }

    public void setQuotes(ArrayList<QuotesDTO> Quotes) {
        this.Quotes = Quotes;
    }

    public ArrayList<PlacesDTO> getPlaces() {
        return Places;
    }

    public void setPlaces(ArrayList<PlacesDTO> Places) {
        this.Places = Places;
    }

    public ArrayList<CarriersDTO> getCarriers() {
        return Carriers;
    }

    public void setCarriers(ArrayList<CarriersDTO> Carriers) {
        this.Carriers = Carriers;
    }

    @Override
    public String toString() {
        return "FlightDTO{" + "Dates=" + Dates + ", Quotes=" + Quotes + ", Places=" + Places + ", Carriers=" + Carriers + '}';
    }
    
    public static void main(String[] args) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String test = HttpUtils.fetchData("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browsedates/v1.0/US/USD/en-US/lond/pari/2020-05/2020-06", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com", "e0d467d76bmsh9ff99e16d60d6c1p11ec83jsn67c7df490a96");
        FlightDTO testDTO = gson.fromJson(test, FlightDTO.class);
        System.out.println(gson.fromJson(test, FlightDTO.class));
    }
    
}

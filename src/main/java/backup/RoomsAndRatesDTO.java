package backup;

import java.util.ArrayList;


public class RoomsAndRatesDTO {
    //ArrayList<RoomsDTO> rooms;
    String bookingUrl;

    public RoomsAndRatesDTO() {
    }

    public RoomsAndRatesDTO(String bookingUrl) {
        this.bookingUrl = bookingUrl;
    }

    

//    public ArrayList<RoomsDTO> getRooms() {
//        return rooms;
//    }
//
//    public void setRooms(ArrayList<RoomsDTO> rooms) {
//        this.rooms = rooms;
//    }

    @Override
    public String toString() {
        return "RoomsAndRatesDTO{" + "bookingUrl=" + bookingUrl + '}';
    }

    
    
    
}

package backup;

import java.util.ArrayList;


class RoomsDTO {
    //ArrayList<ImagesDTO> images;
    String name;
    //ArrayList<RatePlansDTO> ratePlans;

    public RoomsDTO() {
    }

    public RoomsDTO(String name) {
        this.name = name;
    }

   
    /*public ArrayList<RatePlansDTO> getRatePlans() {
        return ratePlans;
    }

    public void setRatePlans(ArrayList<RatePlansDTO> ratePlans) {
        this.ratePlans = ratePlans;
    }*/

    @Override
    public String toString() {
        return "RoomsDTO{" + "name=" + name + '}';
    }


    
    
    
}

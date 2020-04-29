package backup;

import java.util.ArrayList;


public class RoomsDataDTO {
    ArrayList<ImagesDTO> images;
    ArrayList<RatePlansDTO> ratePlans;

    public RoomsDataDTO() {
    }

    public RoomsDataDTO(ArrayList<ImagesDTO> images, ArrayList<RatePlansDTO> ratePlans) {
        this.images = images;
        this.ratePlans = ratePlans;
    }

    public ArrayList<ImagesDTO> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImagesDTO> images) {
        this.images = images;
    }

    public ArrayList<RatePlansDTO> getRatePlans() {
        return ratePlans;
    }

    public void setRatePlans(ArrayList<RatePlansDTO> ratePlans) {
        this.ratePlans = ratePlans;
    }

    @Override
    public String toString() {
        return "RoomsDataDTO{" + "images=" + images + ", ratePlans=" + ratePlans + '}';
    }

    
    
    
}

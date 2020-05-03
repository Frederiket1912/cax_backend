package dtos.hoteldto;

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
        return "HotelDTO{" + "data=" + data + '}';
    }

   
}

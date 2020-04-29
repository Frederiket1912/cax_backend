package backup;

import java.util.ArrayList;


class PropertyDescriptionDTO {
    AddressDTO address;

    public PropertyDescriptionDTO() {
    }

    public PropertyDescriptionDTO(AddressDTO address) {
        this.address = address;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "AddressDTO{" + "address=" + address + '}';
    }
    
    
}

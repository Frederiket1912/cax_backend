package dtos.orderdto;

import dtos.listitemdto.ListItemDTO;
import java.util.List;

public class CheckOrderDTO {
    private String username;
    private List<ListItemDTO> listitems;

    public CheckOrderDTO(String username, List<ListItemDTO> listitems) {
        this.username = username;
        this.listitems = listitems;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ListItemDTO> getListitems() {
        return listitems;
    }

    public void setListitems(List<ListItemDTO> listitems) {
        this.listitems = listitems;
    }
    
    
}

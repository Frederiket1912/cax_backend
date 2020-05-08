package dtos.orderdto;

import dtos.listitemdto.ListItemDTO;
import entities.ListItem;
import entities.Order;
import java.util.ArrayList;
import java.util.List;

public class CheckOrderDTO {

    private String username;
    private List<ListItemDTO> listitems = new ArrayList<>();
    private boolean cancelled;
    private int id;

    public CheckOrderDTO(String username, Order order) {
        this.username = username;
        for (ListItem li : order.getListitems()) {
            listitems.add(new ListItemDTO(li));
        }
        this.cancelled = order.getCancelled();
        this.id = order.getId();

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

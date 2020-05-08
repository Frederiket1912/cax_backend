package dtos.orderdto;

import entities.ListItem;
import java.util.ArrayList;
import java.util.List;

public class CreateOrderDTO {

    String username;
    List<ListItem> listItems;

    public CreateOrderDTO() {
    }

    public CreateOrderDTO(String username, List<ListItem> listItems) {
        this.username = username;
        this.listItems = listItems;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ListItem> getListItems() {
        return listItems;
    }

    public void setListItems(List<ListItem> listItems) {
        this.listItems = listItems;
    }
    
    @Override
    public String toString() {
        return "CreateOrderDTO{" + "username=" + username + ", listItems=" + listItems + '}';
    }
    
    

}

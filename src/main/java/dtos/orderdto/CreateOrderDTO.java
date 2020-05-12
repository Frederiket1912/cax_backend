package dtos.orderdto;

import entities.DiscountCode;
import entities.ListItem;
import java.util.ArrayList;
import java.util.List;

public class CreateOrderDTO {

    String username;
    List<ListItem> listItems;
    DiscountCodeDTO discountCode;

    public CreateOrderDTO() {
    }

    public CreateOrderDTO(String username, List<ListItem> listItems, DiscountCodeDTO discountCode) {
        this.username = username;
        this.listItems = listItems;
        this.discountCode = discountCode;
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

    public DiscountCodeDTO getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(DiscountCodeDTO discountCode) {
        this.discountCode = discountCode;
    }
    
    
    
    @Override
    public String toString() {
        return "CreateOrderDTO{" + "username=" + username + ", listItems=" + listItems + '}';
    }
    
    

}

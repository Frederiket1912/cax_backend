package dtos.listitemdto;

import entities.ListItem;

public class ListItemDTO {
    private int id;
    private String service;
    private String name;
    private String dateIn;
    private String dateOut;
    private int price;
    private int adults;

    public ListItemDTO(ListItem listItem) {
        this.id = listItem.getId();
        this.service = listItem.getService();
        this.name = listItem.getName();
        this.dateIn = listItem.getDateIn();
        this.dateOut = listItem.getDateOut();
        this.price = listItem.getPrice();
        this.adults = listItem.getAdults();
    }

    public int getId() {
        return id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateIn() {
        return dateIn;
    }

    public void setDateIn(String dateIn) {
        this.dateIn = dateIn;
    }

    public String getDateOut() {
        return dateOut;
    }

    public void setDateOut(String dateOut) {
        this.dateOut = dateOut;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }
    
    
    
    
}

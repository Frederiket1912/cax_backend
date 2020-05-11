package dtos.orderdto;

import entities.DiscountCode;

public class DiscountCodeDTO {
    private int id;
    private String name;
    private int discountPercentage;
    private int code;

    public DiscountCodeDTO() {
    }   

    public DiscountCodeDTO(DiscountCode discountCode) {
        this.id = discountCode.getId();
        this.name = discountCode.getName();
        this.discountPercentage = discountCode.getDiscountPercentage();
        this.code = discountCode.getCode();
    }

    public DiscountCodeDTO(String name, int discountPercentage, int code) {
        this.name = name;
        this.discountPercentage = discountPercentage;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    

    @Override
    public String toString() {
        return "DiscountCodeDTO{" + "id=" + id + ", name=" + name + ", discountPercentage=" + discountPercentage + '}';
    }
    
    
}

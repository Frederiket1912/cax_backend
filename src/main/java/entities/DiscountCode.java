package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NamedQuery(name = "DiscountCode.deleteAllRows", query = "DELETE from DiscountCode")
public class DiscountCode implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int discountPercentage;
    @NotNull
    @Size(min = 4, max = 4)
    @Column(unique=true)
    private int code;

    public DiscountCode() {
    }

    public DiscountCode(String name, int discountPercentage, int code) {
        this.name = name;
        this.discountPercentage = discountPercentage;
        this.code = code;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "DiscountCode{" + "id=" + id + ", name=" + name + ", discountPercentage=" + discountPercentage + ", code=" + code + '}';
    }
    
    
}

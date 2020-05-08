package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQuery(name = "Order.deleteAllRows", query = "DELETE from Order")
@Table(name = "orders")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "order_id")
    private int id;
    @OneToMany (cascade = CascadeType.PERSIST)
    private List<ListItem> listitems;
    @Column(name = "cancelled")
    private Boolean cancelled = Boolean.FALSE;
    
    public Order() {
    }
       
    public List<ListItem> getListitems() {
        return listitems;
    }

    public void setListitems(List<ListItem> listitems) {
        this.listitems = listitems;
    }

    public Order(List<ListItem> listitems) {
        this.listitems = listitems;
    }
    
    public int getId() {
        return id;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }
    
    

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", listitems=" + listitems + ", cancelled=" + cancelled + '}';
    }
    
    
    
}

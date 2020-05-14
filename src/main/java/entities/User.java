package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.mindrot.jbcrypt.BCrypt;

@Entity
@NamedQuery(name = "User.deleteAllRows", query = "DELETE from User")
@Table(name = "users")
public class User implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "user_name", length = 25)
  private String userName;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "user_pass")
  private String userPass;
  @JoinTable(name = "user_roles", joinColumns = {
    @JoinColumn(name = "user_name", referencedColumnName = "user_name")}, inverseJoinColumns = {
    @JoinColumn(name = "role_name", referencedColumnName = "role_name")})
  @ManyToMany
  private List<Role> roleList = new ArrayList();
  @JoinTable(name = "user_orders", joinColumns = {
    @JoinColumn(name = "user_name", referencedColumnName = "user_name")}, inverseJoinColumns = {
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")})
  @ManyToMany (cascade = {CascadeType.PERSIST})
  private List<Order> orders = new ArrayList();
  @OneToMany (cascade = {CascadeType.PERSIST})
  private List<SupportTicket> supportticket = new ArrayList();
  
    public void addOrder(Order order){
        this.orders.add(order);
    }
    
    public void addTicket(SupportTicket ticket){
        this.supportticket.add(ticket);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<SupportTicket> getSupportticket() {
        return supportticket;
    }

    public void setSupportticket(List<SupportTicket> supportticket) {
        this.supportticket = supportticket;
    }
    
    

  public List<String> getRolesAsStrings() {
    if (roleList.isEmpty()) {
      return null;
    }
    List<String> rolesAsStrings = new ArrayList();
    for (Role role : roleList) {
      rolesAsStrings.add(role.getRoleName());
    }
    return rolesAsStrings;
  }

  public User() {}


   public boolean verifyPassword(String pw){
        return BCrypt.checkpw(pw, this.userPass);
    }

  public User(String userName, String userPass) {
    this.userName = userName;
    this.userPass = BCrypt.hashpw(userPass, BCrypt.gensalt());
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserPass() {
    return this.userPass;
  }

  public void setUserPass(String userPass) {
    this.userPass = BCrypt.hashpw(userPass, BCrypt.gensalt());
  }

  public List<Role> getRoleList() {
    return roleList;
  }

  public void setRoleList(List<Role> roleList) {
    this.roleList = roleList;
  }

  public void addRole(Role userRole) {
    roleList.add(userRole);
  }

    @Override
    public String toString() {
        return "User{" + "userName=" + userName + ", userPass=" + userPass + ", roleList=" + roleList + '}';
    }
  
  

}

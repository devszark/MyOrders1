package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String description;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<OrderItem> items = new ArrayList<>();

    public Order(String description) {
        this.description = description;
    }

    public void addItem(OrderItem orderItem){
        for (OrderItem oI : items){
            if (oI.getProduct().equals(orderItem.getProduct())){
                oI.setQuantity(oI.getQuantity()+orderItem.getQuantity());
                return;
            }
        }
        items.add(orderItem);
    }

    public void addItem(Product newP, int quantity){
        for (OrderItem oI : items){
            if (oI.getProduct().equals(newP)){
                oI.setQuantity(oI.getQuantity()+quantity);
                return;
            }
        }
        items.add(new OrderItem(newP,quantity));
    }


}

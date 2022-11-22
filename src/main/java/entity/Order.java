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

    public void addItem(OrderItem newOrderItem){
        addItem(newOrderItem.getProduct(), newOrderItem.getQuantity());
    }

    /**
     Adding a new product as a product item
     If the product already exists on the item list - then add only quantity
     */
    public void addItem(Product newP, int quantity)
     {
        /*for (OrderItem oI : items){
            if (oI.getProduct().equals(newP)){
                oI.setQuantity(oI.getQuantity()+quantity);
                return;
            }
          items.add(new OrderItem(newP,quantity));
        }*/

        // Find existing OrderItem which holds newP product already
        OrderItem existingItem = items.stream()
                .filter(item -> item.getProduct().equals(newP))
                .findFirst()
                .orElse(null);
        if (existingItem!=null){
            // if found, just add quantity
            existingItem.setQuantity(existingItem.getQuantity()+quantity);
        } else{
            // if not found, just add another OrderItem
            items.add(new OrderItem(newP,quantity));
        }
    }


}

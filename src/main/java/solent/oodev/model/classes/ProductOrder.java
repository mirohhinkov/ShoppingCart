package solent.oodev.model.classes;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import solent.oodev.model.classes.product.Product;

@Getter @Setter
public class ProductOrder {
    private Product product;
    private int quantity;

    public ProductOrder(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}

package solent.oodev.model.classes.product;

import lombok.Data;

@Data
public class Product {

    private long productId;
    private long categoryId;
    private String categoryDescription;
    private String description;
    private double price;
    private String productUrl;
    private int stock;

}

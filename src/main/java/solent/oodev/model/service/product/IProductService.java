package solent.oodev.model.service.product;

import solent.oodev.model.classes.product.Product;
import solent.oodev.model.classes.user.User;

import java.util.List;

public interface IProductService {



//    public void setDataSource();

    Product getProductById(long id);
    List<Product> getProductsByCategory(int categoryId);
    List<Product> getProductsByCategoryName(String categoryDescription);

    List<Product> getProductsByName(String name);
    public List<Product> getAllProducts();

    boolean addProduct(Product product);

    boolean updateStock(Product product);
}

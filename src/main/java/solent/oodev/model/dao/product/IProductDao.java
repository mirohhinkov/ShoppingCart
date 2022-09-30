package solent.oodev.model.dao.product;

import solent.oodev.model.classes.Cart;
import solent.oodev.model.classes.product.Product;
import solent.oodev.model.classes.user.User;

import java.util.List;

public interface IProductDao {
    Product getProductById(long id);
    List<Product> getProductsByCategory(int categoryId);
    List<Product> getProductsByCategoryName(String categoryDescription);
    List<Product> getAllProducts();
    List<Product> getProductsByName(String name);

    boolean addProduct(Product product);

    boolean updateStock(Product product);

    long placeOrder(User user, Cart cart);

}

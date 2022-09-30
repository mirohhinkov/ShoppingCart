/**
 *
 * @author Miroslav Hinkov
 */
package solent.oodev.model.service.cart;


import solent.oodev.model.classes.Cart;
import solent.oodev.model.classes.product.Product;
import solent.oodev.model.classes.user.User;

public interface ICartService {
    public void addProduct(Product product, int quantity);
    public boolean changeQuantity(Product product, int newQuantity);
    public boolean increase(Product product);
    public boolean decrease(Product product);
    public boolean removeSong(Product product);
    public double cartPrice();
    public long placeOrder(User user);
    public void clearCart();
}



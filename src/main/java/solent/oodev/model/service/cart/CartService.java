/**
 *
 * @author Miroslav Hinkov
 */
package solent.oodev.model.service.cart;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import solent.oodev.model.classes.OrderDetails;
import solent.oodev.model.classes.Cart;
import solent.oodev.model.classes.ProductOrder;
import solent.oodev.model.classes.product.Product;
import solent.oodev.model.classes.user.User;
import solent.oodev.model.dao.product.ProductDao;
import solent.oodev.model.service.user.UserService;

import java.util.List;

@Slf4j
@Service
public class CartService implements ICartService {
    @Autowired
    private Cart cart;
//    private OrderDao orderDao = new OrderDao(); private JdbcTemplate jdbcTemplate;
    private User loggedUser;
    private UserService userService;
    private ProductDao productDao;
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public CartService(Cart cart, JdbcTemplate jdbcTemplate, User loggedUser, UserService userService, ProductDao productDao) {
        this.cart = cart;
        this.jdbcTemplate = jdbcTemplate;
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.productDao = productDao;
    }

    @Override
    public void addProduct(Product product, int quantity) {
        if (quantity <= product.getStock())
            cart.getProductOrders().put(product.getProductId(), new ProductOrder(product, quantity));

    }

    @Override
    public boolean changeQuantity(Product product, int newQuantity) {
        if (newQuantity < 1) return removeSong(product);
        if (newQuantity > product.getStock())
            return false;
        cart.getProductOrders().get(product.getProductId()).setQuantity(newQuantity);
        return true;
    }

    @Override
    public boolean increase(Product product) {
        if (cart.getProductOrders().containsKey(product.getProductId())) {
            int newQuantity = cart.getProductOrders().get(product.getProductId()).getQuantity() + 1;
            return changeQuantity(product, newQuantity);
        }
        return false;
    }

    @Override
    public boolean decrease(Product product) {
        if (cart.getProductOrders().containsKey(product.getProductId())) {
            int newQuantity = cart.getProductOrders().get(product.getProductId()).getQuantity() - 1;
            return changeQuantity(product, newQuantity);
        }
        return false;
    }

    @Override
    public boolean removeSong(Product product) {
        if (cart.getProductOrders().containsKey(product.getProductId())) {
            cart.getProductOrders().remove(product.getProductId());
            return true;
        }
        return false;
    }

    @Override
    public double cartPrice() {
        double cartP = 0;
        for (ProductOrder order : cart.getProductOrders().values())
            cartP += order.getQuantity() * order.getProduct().getPrice();
        return cartP;
    }
    public void clearCart() {
        cart.getProductOrders().clear();
    };
    @Override
    public long placeOrder(User user) {
        log.info(user.toString());
        long orderMade = productDao.placeOrder(user, cart);
        double newBalance = user.getBalance() - cartPrice();
        userService.updateBalance(user, newBalance);
        return orderMade;
    }

    public List<OrderDetails> getUserOrders(User user) {
        return productDao.getUserOrders(user);
    }
}

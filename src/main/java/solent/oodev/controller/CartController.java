package solent.oodev.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import solent.oodev.model.classes.OrderDetails;
import solent.oodev.model.classes.Cart;
import solent.oodev.model.classes.product.Product;
import solent.oodev.model.classes.user.User;
import solent.oodev.model.service.cart.CartService;
import solent.oodev.model.service.product.ProductService;
import solent.oodev.utils.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
public class CartController {
    private Cart cart;
    private CartService cartService;
    private final ProductService productService;

    @Autowired
    public CartController(Cart cart, CartService cartService, ProductService productService) {
        this.cart = cart;
        this.productService = productService;
        this.cartService = cartService;
    }

    // route localhost:8080/add
    @GetMapping(Mappings.ADD_TO_CART)
    public String add(@RequestParam long productId, @RequestParam String filter) {
        if (cart.getProductOrders().get(productId) == null) {
            Product product = productService.getProductById(productId);
            cartService.addProduct(product, 1);
        } else {
            cartService.increase(cart.getProductOrders().get(productId).getProduct());
        }
        return "redirect:/" + ViewNames.FILTERED + "?filter=" + filter;
    }

    // route localhost:8080/cart
    @GetMapping(Mappings.CART)
    public String cartContent(Model model) {
        model.addAttribute(AttributeNames.CART, cart);
        model.addAttribute(AttributeNames.CART_PRICE, cartService.cartPrice());
        return ViewNames.CART;
    }

    // route localhost:8080/cart-action
    @GetMapping(Mappings.ACTIONS)
    public String cartActions(@RequestParam long id, @RequestParam String action) {
        if (cart.getProductOrders().containsKey(id)) {
            switch (action) {
                case "inc":
                  cartService.increase(cart.getProductOrders().get(id).getProduct());
                break;
                case "dec":
                    cartService.decrease(cart.getProductOrders().get(id).getProduct());
                break;
                case "del":
                  cartService.removeSong(cart.getProductOrders().get(id).getProduct());
                break;
            }
        }
        return "redirect:/"+ ViewNames.CART;
    }

    // route localhost:8080/buy
    @GetMapping(Mappings.PLACE_ORDER)
    public String placeOrder(HttpServletRequest request , Model model) {
        User loggedUser = (User) request.getSession().getAttribute("loggedUser");
        try {
            ProtectedRoute route = new ProtectedRoute(loggedUser, "user");
            long orderNumber = cartService.placeOrder(loggedUser);
            model.addAttribute("orderNumber", orderNumber);
            cartService.clearCart();
            return ViewNames.ORDER;
        } catch (UnauthorizedException e) {
            return "redirect:/" + Mappings.LOGIN;
        }
    }

    // route localhost:8080/orders
    @GetMapping(Mappings.ORDERS)
    public String orders(HttpServletRequest request , Model model) {
        User loggedUser = (User) request.getSession().getAttribute("loggedUser");
        try {
            ProtectedRoute route = new ProtectedRoute(loggedUser, "user");
            List<OrderDetails> orders = cartService.getUserOrders(loggedUser);
            model.addAttribute("orders", orders);
            return ViewNames.ORDERS;
        } catch (UnauthorizedException e) {
            return "redirect:/" + Mappings.LOGIN;
        }
    }

}

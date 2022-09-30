package solent.oodev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import solent.oodev.model.classes.Cart;
import solent.oodev.model.classes.product.Product;
import solent.oodev.model.classes.user.User;
import solent.oodev.model.service.product.ProductService;
import solent.oodev.utils.AttributeNames;
import solent.oodev.utils.Mappings;
import solent.oodev.utils.ViewNames;

import java.util.List;


// Handle user routes
@Controller
public class ProductController {
    private final ProductService productService;
    private final Cart cart;

    public ProductController(@Autowired ProductService productService, @Autowired Cart cart) {
        this.productService = productService;
        this.cart = cart;
    }

    // route localhost:8080/products
    @GetMapping(Mappings.PRODUCTS)
    public String products(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute(AttributeNames.PRODUCTS, products);
        model.addAttribute(AttributeNames.CART, cart);
        return ViewNames.PRODUCTS;
    }

    // route localhost:8080/filtered
    @GetMapping(Mappings.FILTERED)
    public String filteredProducts(@RequestParam String filter, Model model) {
        if (filter.isEmpty()) return "redirect:/" + Mappings.PRODUCTS;
        List<Product> products = productService.getProductsByCategoryName(filter);
        if ((products == null) || products.size() == 0) {
            products = productService.getProductsByName(filter);
        }
        model.addAttribute(AttributeNames.FILTER, filter);
        model.addAttribute(AttributeNames.PRODUCTS, products);
        model.addAttribute("cart", cart);
        return ViewNames.PRODUCTS;
    }

    // route localhost:8080/search
    @GetMapping(Mappings.SEARCH)
    public String search(@RequestParam String productSearch) {
        return "redirect:/" + ViewNames.FILTERED + "?filter=" + productSearch;
    }

    // route localhost:8080/add-product
    @GetMapping(Mappings.ADD_PRODUCT)
    public String addProduct(Model model) {
        Product product = new Product();
        model.addAttribute(AttributeNames.PRODUCT, product);
        return ViewNames.ADD_PRODUCT;
    }

    @PostMapping(Mappings.ADD_PRODUCT)
    public String addProd(@ModelAttribute(AttributeNames.PRODUCT) Product product, Model model) {
        if (!productService.addProduct(product)) return "redirect:/" + ViewNames.SERVER_ERROR;
        return "redirect:/" + ViewNames.ADMIN;
    }

    // route localhost:8080/update
    @GetMapping(Mappings.UPDATE_STOCK)
    public String updateStock(Model model) {
        Product product = new Product();
        model.addAttribute(AttributeNames.PRODUCT, product);
        return ViewNames.UPDATE_STOCK;
    }

    @PostMapping(Mappings.UPDATE_STOCK)
    public String update(@ModelAttribute(AttributeNames.PRODUCT) Product product, Model model) {
        if (!productService.updateStock(product)) return "redirect:/" + ViewNames.SERVER_ERROR;
        return "redirect:/" + ViewNames.ADMIN;
    }

}

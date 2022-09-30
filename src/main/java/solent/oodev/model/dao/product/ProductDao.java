package solent.oodev.model.dao.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import solent.oodev.model.classes.OrderDetails;
import solent.oodev.model.classes.Cart;
import solent.oodev.model.classes.OrderDetailsRowMapper;
import solent.oodev.model.classes.ProductOrder;
import solent.oodev.model.classes.product.Product;
import solent.oodev.model.classes.product.ProductRowMapper;
import solent.oodev.model.classes.user.User;
import solent.oodev.utils.UserInputUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class ProductDao implements IProductDao {

    private JdbcTemplate jdbcTemplate;
    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Product getProductById(long id) {
        String sqlQuery =
                "SELECT p.*, c.category_description AS cd FROM products p JOIN categories c USING(category_id) WHERE p.product_id = ?";
        return jdbcTemplate.queryForObject(sqlQuery,
                new ProductRowMapper(), new Object[] { Long.valueOf(id) });
    }

    @Override
    public List<Product> getProductsByCategory(int categoryId) {
        return null;
    }

    @Override
    public List<Product> getProductsByCategoryName(String categoryDescription) {
        try {
            String sqlQuery = "SELECT category_id FROM categories WHERE category_description = ?";
            long orderId = jdbcTemplate.queryForObject(sqlQuery, new RowMapper<Long>() {
                @Override
                public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
                    long id = rs.getLong("category_id");
                    return id;
                }
            }, categoryDescription);
            if (orderId < 1) return null;
            sqlQuery = "SELECT p.*, c.category_description AS cd FROM products p JOIN categories c USING(category_id) WHERE p.category_id = ?";
            return jdbcTemplate.query(sqlQuery,
                    new ProductRowMapper(), new Object[]{Long.valueOf(orderId)});
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Product> getProductsByName(String name) {
        String newName = UserInputUtils.capitalizeWords(UserInputUtils.removeSpecialCharacters(name));
        String sqlQuery ="SELECT p.*, c.category_description AS cd FROM products p JOIN categories c USING(category_id) WHERE " +
                UserInputUtils.prepareForSqlQuery("p.description", newName);
        return jdbcTemplate.query(sqlQuery,
                new ProductRowMapper());
    }

    @Override
    public List<Product> getAllProducts() {
        String sqlQuery =
                "SELECT p.*, c.category_description AS cd FROM products p JOIN categories c USING(category_id); ";
        List<Product> productList = jdbcTemplate.query(sqlQuery, new ProductRowMapper());
        return Collections.unmodifiableList(productList);
    }

    @Override
    public boolean addProduct(Product product) {
        String sqlQuery = "INSERT INTO products(category_id, description, price, imgUrl, stock) " +
                "VALUES (%1%, %2%, %3%, %4%, %5%)"
                .replace("%1%", "\"" + product.getCategoryId() + "\"")
                .replace("%2%", "\"" + product.getDescription() + "\"")
                .replace("%3%", "\"" + product.getPrice() + "\"")
                .replace("%4%", "\"" + product.getProductUrl() + "\"")
                .replace("%5%", "\"" + product.getStock() + "\"");
        try {
            jdbcTemplate.execute(sqlQuery);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateStock(Product product) {
        String sqlQuery = "UPDATE products SET stock=? WHERE product_id=?";
        try {
            jdbcTemplate.update(sqlQuery,
                    new Object[] {Integer.valueOf(product.getStock()), product.getProductId()});
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public long placeOrder(User user, Cart cart) {
        //    place order
        String sqlQuery = "INSERT INTO user_orders(user_id) VALUES (?)".
                replace("?", "" + user.getUserID());
        jdbcTemplate.execute(sqlQuery);

        //      getting new order number
        sqlQuery = "SELECT order_id FROM user_orders ORDER BY order_id DESC LIMIT 1";
        long orderId = jdbcTemplate.queryForObject(sqlQuery, new RowMapper<Long>() {
            @Override
            public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getLong("order_id");
                return id;
            }
        });

        //      placing ordered products

        for (ProductOrder order : cart.getProductOrders().values()) {
            // place the ordered products in ordered-products
            int orderedQuantity = order.getQuantity();
            sqlQuery = "INSERT INTO ordered_products(order_id, product_id, quantity) VALUES (?,!,#)"
                    .replace("?", "" + orderId)
                    .replace("!", "" + order.getProduct().getProductId())
                    .replace("#", "" + orderedQuantity);
            jdbcTemplate.execute(sqlQuery);
            int newStock = order.getProduct().getStock() - orderedQuantity;
            // update the stock
            sqlQuery = "UPDATE products SET stock=? WHERE product_id=?";
//                    .replace("?", "" + newStock)
//                    .replace("#", "" + order.getProduct().getProductId());
            jdbcTemplate.update(sqlQuery,
                    new Object[] {Integer.valueOf(newStock), order.getProduct().getProductId()});
        }
        return orderId;
    }

    public List<OrderDetails> getUserOrders(User user) {
        String sqlQuery = "SELECT o.order_id, o.order_date, op.quantity, op.product_id, p.description FROM user_orders o join ordered_products op Using (order_id) join products p ON p.product_id = op.product_id WHERE o.user_id=? ORDER BY o.order_id";
        return jdbcTemplate.query(sqlQuery, new OrderDetailsRowMapper(), new Object[] {user.getUserID()});
    }
}

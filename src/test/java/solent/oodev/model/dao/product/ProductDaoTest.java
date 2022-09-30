package solent.oodev.model.dao.product;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import solent.oodev.model.classes.product.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoTest {

    private JdbcTemplate jdbcTemplate;
    private ProductDao productDao;
    public ProductDaoTest() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:mysql://localhost:3306/AE2?useSSL=false");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.productDao = new ProductDao(this.jdbcTemplate);
    }

    @Test
    void getProductById() {
        long prodId = 3000000;
        Product product = productDao.getProductById(prodId);
        assertEquals(prodId, product.getProductId());
    }

    @Test
    void getAllProducts() {
        List<Product> products = productDao.getAllProducts();
        assertTrue(products.size() > 0);
    }
}
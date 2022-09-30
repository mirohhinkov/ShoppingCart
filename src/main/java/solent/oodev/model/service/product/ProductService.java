package solent.oodev.model.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import solent.oodev.model.classes.product.Product;
import solent.oodev.model.dao.product.IProductDao;
import solent.oodev.model.dao.product.ProductDao;

import java.util.List;
@Service
public class ProductService implements IProductService{

    private IProductDao productDao;

    @Autowired
    public ProductService(JdbcTemplate jdbcTemplate) {
        this.productDao = new ProductDao(jdbcTemplate);
    }
    @Override
    public Product getProductById(long id) {
        return productDao.getProductById(id);
    }

    @Override
    public List<Product> getProductsByCategory(int categoryId) {
        return null;
    }

    @Override
    public List<Product> getProductsByCategoryName(String categoryDescription) {
        return productDao.getProductsByCategoryName(categoryDescription);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productDao.getProductsByName(name);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public boolean addProduct(Product product) {
        return productDao.addProduct(product);
    }

    @Override
    public boolean updateStock(Product product) {
        return productDao.updateStock(product);
    }
}

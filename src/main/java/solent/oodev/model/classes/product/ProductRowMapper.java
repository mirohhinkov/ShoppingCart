package solent.oodev.model.classes.product;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setProductId(rs.getLong("p.product_id"));
        product.setCategoryId(rs.getLong("p.category_id"));
        product.setCategoryDescription(rs.getString("cd"));
        product.setDescription(rs.getString("p.description"));
        product.setPrice(rs.getDouble("p.price"));
        product.setProductUrl(rs.getString("p.imgUrl"));
        product.setStock(rs.getInt("stock"));
        return product;
    }
}

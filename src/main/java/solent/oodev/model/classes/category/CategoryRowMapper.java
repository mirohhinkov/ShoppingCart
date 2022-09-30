package solent.oodev.model.classes.category;

import org.springframework.jdbc.core.RowMapper;
import solent.oodev.model.classes.product.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRowMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category category = new Category();
        category.setCategoryId(rs.getLong("category_id"));
        category.setCategotyDescription("category_description");
        return category;
    }
}

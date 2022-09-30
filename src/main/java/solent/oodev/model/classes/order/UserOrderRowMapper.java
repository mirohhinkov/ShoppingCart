package solent.oodev.model.classes.order;

import org.springframework.jdbc.core.RowMapper;
import solent.oodev.model.classes.category.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserOrderRowMapper implements RowMapper<UserOrder>  {
    public UserOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserOrder userOrder = new UserOrder();
        userOrder.setUserId(rs.getLong("order_id"));
        userOrder.setUserId(rs.getLong("user_id"));
        userOrder.setDate(rs.getDate("order_date"));
        return userOrder;
    }
}

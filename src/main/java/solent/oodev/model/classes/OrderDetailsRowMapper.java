package solent.oodev.model.classes;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailsRowMapper implements RowMapper<OrderDetails> {
    @Override
    public OrderDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setOrderId(rs.getLong("o.order_id"));
        orderDetails.setOrderDate(rs.getDate("o.order_date"));
        orderDetails.setProdDescription(rs.getString("p.description"));
        orderDetails.setProductId(rs.getLong("op.product_id"));
        orderDetails.setQuantity(rs.getInt("op.quantity"));
        return orderDetails;
    }
}

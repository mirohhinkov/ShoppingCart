package solent.oodev.model.classes.user;

import org.springframework.jdbc.core.RowMapper;
import solent.oodev.model.classes.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserID(rs.getInt("user_id"));
        user.setUserName(rs.getString("user_name"));
        user.setUserEmail(rs.getString("email"));
        user.setBalance(rs.getDouble("ballance"));
        user.setAdmin(rs.getBoolean("admin"));

        return user;
    }
}
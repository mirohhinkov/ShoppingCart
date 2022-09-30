package solent.oodev.model.dao.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import solent.oodev.model.classes.user.User;
import solent.oodev.model.classes.user.*;
import solent.oodev.utils.UserInputUtils;

import java.util.Collections;
import java.util.List;
@Slf4j
@Component
public class UserDao implements IUserDao {

    private JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean create(User user) {
        String sqlQuery = "INSERT INTO users (user_name, email, password, ballance) VALUES (?, #, $, 0)"
                .replace("?", "\"" + UserInputUtils.removeSpecialCharacters(user.getUserName()) + "\"")
                .replace("#", "\"" + user.getUserEmail() + "\"")
                .replace("$", "\"" + user.getPassword() + "\"");
        try {
            jdbcTemplate.execute(sqlQuery);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public User getUserById(int id) {
         String sqlQuery ="SELECT * FROM users WHERE user_id = ?";
         System.out.println(id);
         User user = jdbcTemplate.queryForObject(sqlQuery,
                new UserRowMapper(), new Object[] { Long.valueOf(id) });
         return user;
    }

    @Override
    public User getUserByIEmail(String email) {
        String sqlQuery ="SELECT * FROM users WHERE email = ?";
        try {
            User user = jdbcTemplate.queryForObject(sqlQuery,
                    new UserRowMapperPass(), new Object[]{email});
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        String sqlQuery ="SELECT * FROM users";
        List<User> userList = jdbcTemplate.query(sqlQuery, new UserRowMapper());
        return Collections.unmodifiableList(userList);
    }



    @Override
    public boolean deleteUser(User user) {
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public void updateBalance(User user, double newBalance) {
        String sqlQuery = "UPDATE users SET ballance=%1% WHERE user_id=%2%"
                .replace("%1%", "" + newBalance)
                .replace("%2%", "" + user.getUserID());
        jdbcTemplate.execute(sqlQuery);
    }
}

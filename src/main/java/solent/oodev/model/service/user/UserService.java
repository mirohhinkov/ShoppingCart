package solent.oodev.model.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import solent.oodev.model.classes.user.User;
import solent.oodev.model.dao.user.IUserDao;
import solent.oodev.model.dao.user.UserDao;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserService implements IUserService {
    private IUserDao userDao;
    @Autowired
    User loggedUser;

    @Autowired
    public UserService(JdbcTemplate jdbcTemplate) {
        this.userDao = new UserDao(jdbcTemplate);
    }


    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByIEmail(email);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public boolean createUser(User user) throws SQLException {
        return userDao.create (user);
    }

    public void updateBalance(User user, double newBalance) {
        userDao.updateBalance(user, newBalance);
    }
}

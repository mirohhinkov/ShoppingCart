package solent.oodev.model.service.user;

import solent.oodev.model.classes.user.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserService {



//    public void setDataSource();

    User getUserById(int id);
    User getUserByEmail(String email);

    List<User> getAllUsers();

    boolean createUser(User user) throws SQLException;

    public void updateBalance(User user, double newBalance);

}

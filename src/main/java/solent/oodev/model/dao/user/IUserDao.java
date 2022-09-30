package solent.oodev.model.dao.user;

import solent.oodev.model.classes.user.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDao {
    // Set the data source required to connect to MySql database
//    void setDataSource(DataSource dataSource);

    // CREATE record in table
    boolean create(User user) throws SQLException;

    // READ concrete User from DB, as parameter - id
    User getUserById(int id);

    // READ concrete User from DB, as parameter - email
    User getUserByIEmail(String email);

    // READ all Users
    List<User> getAllUsers();

    //DELETE concrete User
    boolean deleteUser(User User);

    //UPDATE an existing User
    boolean updateUser(User User);

    void updateBalance(User user, double newBalance);
}

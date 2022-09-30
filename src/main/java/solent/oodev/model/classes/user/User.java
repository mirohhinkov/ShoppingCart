package solent.oodev.model.classes.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "userID")
public class User {
    private int userID;
    private String userName;
    private String userEmail;
    private String password;
    private String passConfirm;
    private double balance;
    private boolean admin;
}

package solent.oodev.utils;

import solent.oodev.model.classes.user.User;

public class ProtectedRoute {
    public ProtectedRoute(User loggedUser, String level) throws UnauthorizedException {
        if (!level.isEmpty()) {
            if (loggedUser == null)
                throw new UnauthorizedException("This page require a login");
            if (level.equals("user") && loggedUser.isAdmin())
                throw new UnauthorizedException("This page require user login");
            if (level.equals("admin") && !loggedUser.isAdmin())
                throw new UnauthorizedException("This page require admin login");
        }
    }
}

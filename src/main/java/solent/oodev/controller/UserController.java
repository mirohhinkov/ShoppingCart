package solent.oodev.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import solent.oodev.model.classes.user.User;
import solent.oodev.model.service.user.UserService;
import solent.oodev.utils.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;


// Handle user routes
@Slf4j
@Controller
public class UserController {
    private final UserService userService;
    private User loggedUser;
    private String message;
    @Autowired
    public UserController(UserService userService, User loggedUser, String message) {
        this.userService = userService;
        this.message = message;
        this.loggedUser = loggedUser;
    }

    // route localhost:8080/admin
    @GetMapping(Mappings.ADMIN)
    public String admin(Model model) throws UnauthorizedException {
        try {
            ProtectedRoute route = new ProtectedRoute(loggedUser, "admin");
            return ViewNames.ADMIN;
        } catch (UnauthorizedException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return ViewNames.UNAUTHORIZED;
        }
    }

    // route localhost:8080/login
    @GetMapping(Mappings.LOGIN)
    public String login(Model model, HttpServletRequest request) {
        if (loggedUser.getUserName() != null) {
            loggedUser = new User();
            request.getSession().setAttribute("loggedUser", null);
            return "redirect:/" + Mappings.PRODUCTS;
        }
        User user = new User();
        model.addAttribute("message", this.message);
        model.addAttribute(AttributeNames.USER, user);
        return ViewNames.LOGIN;
    }

    @PostMapping(Mappings.LOGIN)
    public String login(@ModelAttribute(AttributeNames.USER) User user, HttpServletRequest request) {
        loggedUser = userService.getUserByEmail(user.getUserEmail());
        try {
            if (user.getPassword().equals(loggedUser.getPassword())) {
                request.getSession().setAttribute("loggedUser", loggedUser);
                this.message = "";
                if (loggedUser.isAdmin()) return "redirect:/" + Mappings.ADMIN;
                return "redirect:/" + Mappings.CART;
            } else {
                this.message = "Invalid password";
                return "redirect:/" + Mappings.LOGIN;
            }
        } catch (Exception e) {
            this.message = "Invalid email";
            return "redirect:/" + Mappings.LOGIN;
        }
    }

    // route localhost:8080/signup
    @GetMapping(Mappings.SIGNUP)
    public String signup(@Autowired String message, Model model) {
        User user = new User();
        model.addAttribute("message", this.message);
        model.addAttribute(AttributeNames.USER, user);
        return ViewNames.SIGNUP;
    }

    @PostMapping(Mappings.SIGNUP)
    public String signup(@ModelAttribute(AttributeNames.USER) User user,
                         HttpServletRequest request, Model model) throws SQLException {
        User dbUser = userService.getUserByEmail(user.getUserEmail());
        if (dbUser == null) {
            if (user.getPassword().equals(user.getPassConfirm())) {
                try {
                    userService.createUser(user);
                    this.message = "";
                    loggedUser = user;
                    request.getSession().setAttribute("loggedUser", loggedUser);
                    return "redirect:/" + Mappings.CART;
                } catch (SQLException e) {
                    model.addAttribute("errorMessage", e.getMessage());
                    return ViewNames.SERVER_ERROR;
                }
            } else {
                this.message = "Passwords do not match";
                return "redirect:/" + Mappings.SIGNUP;
            }
        } else {
            this.message = "Email " + user.getUserEmail() + " already in use";
            return "redirect:/" + Mappings.SIGNUP;
        }

    }
}

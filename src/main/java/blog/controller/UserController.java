package blog.controller;

import blog.common.CurrentUser;
import blog.form.RegisterNewUser;
import blog.model.User;
import blog.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;

    @RequestMapping("/users/login")
    private String loginPage(User user) {

        return "users/login";
    }

    @RequestMapping(value = "/users/login", method = RequestMethod.POST)
    public String login(RegisterNewUser user, Model model) {

        if (userServiceImp.authenticate(user.getUsername(), user.getPassword())) {
            CurrentUser.getInstance().setUserName(user.getUsername());
            return "redirect:/posts";
        }

        return "redirect:/";
    }

    @RequestMapping("/users/logout")
    public String logout(User user, Model model) {
        return "redirect:/";
    }

    @RequestMapping("/users/register")
    public String register(RegisterNewUser registerNewUser) {
        return "users/register";
    }

    @RequestMapping(value = "/users/register", method = RequestMethod.POST)
    public String registerUser(RegisterNewUser registerNewUser){
        User user = new User();
        user.setUsername(registerNewUser.getUsername());
        user.setFullName(registerNewUser.getFullName());
        user.setPasswordHash(registerNewUser.getPassword());
        userServiceImp.registerNewUser(user);
        return "redirect:/";
    }
}

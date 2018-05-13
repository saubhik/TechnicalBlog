package blog.services;

import blog.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Override
    public boolean authenticate(String username, String password) {

        if (username.equals("test") && password.equals("test1")) {
            return true;
        }

        return false;
    }

    @Override
    public boolean registerNewUser(User user) {
        return false;
    }
}

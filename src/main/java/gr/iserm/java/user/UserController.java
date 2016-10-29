package gr.iserm.java.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/users")
    public List<User> allUsersFromTemplate() {
        return userService.getAll();
    }

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public User userByName(@RequestParam(value = "name", required = false) String name) {
        return userService.getUserByName(name)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
    }

    @RequestMapping(path = "/user", method = RequestMethod.PUT, consumes = "application/json")
    public void addUser(@RequestBody User user) {
        userService.add(user);
    }
}

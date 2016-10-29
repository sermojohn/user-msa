package gr.iserm.java.user;

import gr.iserm.java.user.processing.UserEnrichment;
import gr.iserm.java.user.processing.UserValidity;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ProducerTemplate processUserInput;

    public List<User> getAll() {
        return userDao.findAll();
    }

    public Optional<User> getUserByName(String name) {
        return userDao.findByName(name);
    }

    public void add(User user) {
        Optional<User> existingUser = userDao.findByName(user.getName());
        Assert.isTrue(!existingUser.isPresent(), "User already exists.");
        processUserInput.asyncSendBody("seda:init", user);
    }

    public UserValidity validate(User user) {
        if(user.getUuid() == null ||
                StringUtils.isEmpty(user.getName()) || StringUtils.isEmpty(user.getEmail())) {
            return new UserValidity(false);
        }

        return new UserValidity(true);
    }

    public UserEnrichment enrich(User user) {
        return new UserEnrichment(UUID.randomUUID().toString());
    }
}

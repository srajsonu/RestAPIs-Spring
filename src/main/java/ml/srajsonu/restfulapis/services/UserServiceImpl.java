package ml.srajsonu.restfulapis.services;

import ml.srajsonu.restfulapis.model.UserDetailsRequest;
import ml.srajsonu.restfulapis.model.UserRest;
import ml.srajsonu.restfulapis.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    Map<String, UserRest> users;
    Utils utils;

    @Autowired
    public UserServiceImpl(Utils utils) {
        this.utils = utils;
    }

    @Override
    public UserRest createUser(UserDetailsRequest userDetails) {

        UserRest userRest = new UserRest();
        userRest.setFirstName(userDetails.getFirstName());
        userRest.setLastName(userDetails.getLastName());
        userRest.setEmail(userDetails.getEmail());

        String userId = utils.generateUserId();
        userRest.setUserId(userId);

        if (users == null) users = new HashMap<>();
        users.put(userId, userRest);

        return userRest;
    }
}

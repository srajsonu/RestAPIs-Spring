package ml.srajsonu.restfulapis.services;

import ml.srajsonu.restfulapis.model.UserDetailsRequest;
import ml.srajsonu.restfulapis.model.UserRest;

public interface UserService {
    public UserRest createUser(UserDetailsRequest userDetails);
}

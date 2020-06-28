package ml.srajsonu.restfulapis.services;

import ml.srajsonu.restfulapis.model.UserDetailsRequest;
import ml.srajsonu.restfulapis.model.UserRest;

public interface UserService {
    UserRest createUser(UserDetailsRequest userDetails);
}

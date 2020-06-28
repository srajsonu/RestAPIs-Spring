package ml.srajsonu.restfulapis.controller;

import ml.srajsonu.restfulapis.exceptions.UserServiceException;
import ml.srajsonu.restfulapis.model.UpdateUserDetails;
import ml.srajsonu.restfulapis.model.UserRest;
import ml.srajsonu.restfulapis.model.UserDetailsRequest;
import ml.srajsonu.restfulapis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    Map<String, UserRest> users;

    @Autowired
    UserService userService;

    @GetMapping
    public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                           @RequestParam(value = "limit", defaultValue = "50") int limit) {
        return "get user with page = "+page + " limit = "+ limit;
    }

    @GetMapping(path = "/{userId}", produces = {MediaType.APPLICATION_XML_VALUE,
                                                MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRest> getUser(@PathVariable String userId) {

        //if (true) throw new UserServiceException("A User Service Exception is thrown");

        if (users.containsKey(userId)) {
            return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
        } else {
            throw new UserServiceException("A User Service Exception is thrown");
            //return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE,
                             MediaType.APPLICATION_JSON_VALUE},
                 produces = {MediaType.APPLICATION_XML_VALUE,
                        MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequest userDetails) {

        UserRest userRest = userService.createUser(userDetails);

        if (users == null) users = new HashMap<>();
        users.put(userRest.getUserId(), userRest);

        return new ResponseEntity<>(userRest, HttpStatus.OK);
    }

    @PutMapping(path = "/{userId}", consumes = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    public UserRest updateUser(@PathVariable String userId,
                             @Valid @RequestBody UpdateUserDetails updateUser) {
        UserRest userDetails = users.get(userId);
        userDetails.setFirstName(updateUser.getFirstName());
        userDetails.setLastName(updateUser.getLastName());

        users.put(userId, userDetails);
        return userDetails;
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        users.remove(userId);
        return ResponseEntity.noContent().build();
    }
}

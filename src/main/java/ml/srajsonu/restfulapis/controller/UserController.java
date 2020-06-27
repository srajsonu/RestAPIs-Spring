package ml.srajsonu.restfulapis.controller;

import ml.srajsonu.restfulapis.model.UpdateUserDetails;
import ml.srajsonu.restfulapis.model.UserRest;
import ml.srajsonu.restfulapis.model.UserDetailsRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    Map<String, UserRest> users;

    @GetMapping
    public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                           @RequestParam(value = "limit", defaultValue = "50") int limit) {
        return "get user with page = "+page + " limit = "+ limit;
    }

    @GetMapping(path = "/{userId}", produces = {MediaType.APPLICATION_XML_VALUE,
                                                MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRest> getUser(@PathVariable String userId) {

        if (users.containsKey(userId)) {
            return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE,
                             MediaType.APPLICATION_JSON_VALUE},
                 produces = {MediaType.APPLICATION_XML_VALUE,
                        MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequest userDetails) {

        UserRest userRest = new UserRest();
        userRest.setFirstName(userDetails.getFirstName());
        userRest.setLastName(userDetails.getLastName());
        userRest.setEmail(userDetails.getEmail());

        String userId = UUID.randomUUID().toString();
        userRest.setUserId(userId);

        if (users == null) users = new HashMap<>();
        users.put(userId, userRest);

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

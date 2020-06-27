package ml.srajsonu.restfulapis.controller;

import ml.srajsonu.restfulapis.model.UserRest;
import ml.srajsonu.restfulapis.model.UserDetailsRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class userController {

    @GetMapping
    public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                           @RequestParam(value = "limit", defaultValue = "50") int limit) {
        return "get user with page = "+page + " limit = "+ limit;
    }

    @GetMapping(path = "/{userId}", produces = {MediaType.APPLICATION_XML_VALUE,
                                                MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRest> getUser(@PathVariable String userId) {

        UserRest userRest = new UserRest();
        userRest.setFirstName("Klaus");
        userRest.setLastName("Mikaelson");
        userRest.setEmail("a@b.com");
        userRest.setUserId(userId);

        return new ResponseEntity<>(userRest, HttpStatus.OK);
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

        return new ResponseEntity<>(userRest, HttpStatus.OK);
    }

    @PutMapping
    public String updateUser() {
        return "update user!";
    }

    @DeleteMapping
    public String deleteUser() {
        return "delete user!";
    }
}

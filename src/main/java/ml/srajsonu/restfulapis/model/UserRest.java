package ml.srajsonu.restfulapis.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserRest {

    @NotNull(message = "First name can't be null")
    private String firstName;

    @NotNull(message = "Last name can't be null")
    private String lastName;

    @NotNull(message = "Email name can't be null")
    private String email;

    @NotNull(message = "UserId name can't be null")
    private String userId;
}

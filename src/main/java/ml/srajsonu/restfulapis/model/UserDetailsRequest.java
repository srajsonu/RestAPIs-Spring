package ml.srajsonu.restfulapis.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserDetailsRequest {

    @NotNull(message = "First name can't be null")
    private String firstName;

    @NotNull(message = "Last name can't be null")
    private String lastName;

    @NotNull(message = "Email name can't be null")
    private String email;

    @NotNull(message = "Password name can't be null")
    @Size(min = 8, max = 16, message = "Password must greater than 8 char and less than 16")
    private String password;
}

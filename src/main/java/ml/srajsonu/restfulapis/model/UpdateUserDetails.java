package ml.srajsonu.restfulapis.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateUserDetails {

    @NotNull(message = "First name can't be null")
    private String firstName;

    @NotNull(message = "Last name can't be null")
    private String lastName;
}

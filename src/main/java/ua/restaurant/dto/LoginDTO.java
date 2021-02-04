package ua.restaurant.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

// income, for registration

public class LoginDTO {
    @NotBlank (message = "{error.signup.login}")
    private String login;

    @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$",
            message = "{error.signup.email}")
    private String email;

    @NotBlank
    @Pattern(regexp = "^[a-z0-9._%+-]{2,6}$",
            message = "{error.signup.password}")
    private String password;
}

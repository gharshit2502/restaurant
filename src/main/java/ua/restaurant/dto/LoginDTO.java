package ua.restaurant.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LoginDTO {
    @NotBlank
    private String login;
    @Email(message = "${validatedValue} is not valid")
    private String email;
    @NotBlank
    private String password;
}

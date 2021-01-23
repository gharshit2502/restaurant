package ua.restaurant.dto;

import lombok.*;
import ua.restaurant.entity.Login;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LoginsDTO {
    private List<Login> logins;
}

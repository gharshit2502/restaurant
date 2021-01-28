package ua.restaurant.dto;

import lombok.*;
import ua.restaurant.entity.Logins;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LoginsDTO {
    private List<Logins> logins;
}

package ua.restaurant.dto;

import lombok.*;
import org.springframework.context.i18n.LocaleContextHolder;
import static ua.restaurant.config.Constants.LANGUAGE_EN;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CategoryDTO {
    private Long id;
    private String category;
}

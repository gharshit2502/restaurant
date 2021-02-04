package ua.restaurant.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

// no validation, send to main page

public class PageableDishesDTO {
    private List<DishDTO> dishes;
    private int currentPage;
    private int totalPages;
    private String sortField;
    private String sortDirection;
}

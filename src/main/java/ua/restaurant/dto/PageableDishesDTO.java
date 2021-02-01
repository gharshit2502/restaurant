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

public class PageableDishesDTO {
    private List<DishDTO> dishes;
    private int currentPage;
    private int totalPages;
    private String sortField;
    private String sortDirection;
}

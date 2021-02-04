package ua.restaurant.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

// TODO use (+ valid) or delete

public class CategoryDTO {
    private Long id;
    private String category;
}

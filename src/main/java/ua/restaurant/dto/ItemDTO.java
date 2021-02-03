package ua.restaurant.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ItemDTO {
    @Min(value = 1, message = "Id should be non-negative number.")
    Long itemId;
}

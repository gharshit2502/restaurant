package ua.restaurant.dto;

import lombok.*;

import javax.validation.constraints.Min;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

// income:
// - for confirm,
// - for payment,
// - for add dish to basket,
// - for delete dish from basket

public class ItemDTO {
    @Min(value = 1, message = "{error.itemDTO}")
    Long itemId;
}

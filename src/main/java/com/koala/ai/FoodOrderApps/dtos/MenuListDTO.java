package com.koala.ai.FoodOrderApps.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuListDTO {
    private Long id; // Include if needed
    private String name;
    private Double price;
    private String image; // Add image field
    private String restaurantId; // Add restaurantId if needed
}

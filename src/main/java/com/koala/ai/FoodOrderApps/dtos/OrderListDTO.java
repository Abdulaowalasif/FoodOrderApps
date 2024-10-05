package com.koala.ai.FoodOrderApps.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderListDTO {
    private Long id;
    private String restaurantId;
    private Long menuId;
    private String image;
    private int quantity;
    private String itemName;
    private String location;
    private String username;
    private String phone;
    private Double totalAmount;
    private String description;
}

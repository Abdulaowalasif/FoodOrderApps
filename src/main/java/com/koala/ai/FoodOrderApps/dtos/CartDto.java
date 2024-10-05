package com.koala.ai.FoodOrderApps.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private Long id;
    private Long menuListId; // ID of the MenuList item
    private Integer quantity;
    private String userId;
}

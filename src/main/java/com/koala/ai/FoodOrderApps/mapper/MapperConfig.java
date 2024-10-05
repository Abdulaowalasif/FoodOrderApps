package com.koala.ai.FoodOrderApps.mapper;


import com.koala.ai.FoodOrderApps.dtos.CartDto;
import com.koala.ai.FoodOrderApps.dtos.MenuListDTO;
import com.koala.ai.FoodOrderApps.dtos.OrderListDTO;
import com.koala.ai.FoodOrderApps.dtos.RestaurantDTO;
import com.koala.ai.FoodOrderApps.entities.Cart;
import com.koala.ai.FoodOrderApps.entities.Restaurant;
import com.koala.ai.FoodOrderApps.entities.MenuList;
import com.koala.ai.FoodOrderApps.entities.OrderList;
import com.koala.ai.FoodOrderApps.services.MenuListService;
import com.koala.ai.FoodOrderApps.services.RestaurantService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperConfig {

    private final ModelMapper modelMapper = new ModelMapper();

    // Convert MenuListDTO to MenuList
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private MenuListService menuListService;

    public MenuList toMenuList(MenuListDTO dto) {
        MenuList menuList = new MenuList();
        menuList.setId(dto.getId());
        menuList.setName(dto.getName());
        menuList.setPrice(dto.getPrice());
        menuList.setPrice(dto.getPrice());
        menuList.setDescription(dto.getDescription());
        menuList.setImage(dto.getImage()); // Map image field
        if (dto.getRestaurantId() != null) {
            Restaurant restaurant = restaurantService.getRestaurantById(dto.getRestaurantId())
                    .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));
            menuList.setRestaurant(restaurant);
        }
        return menuList;
    }

    public MenuListDTO toMenuListDTO(MenuList menuList) {
        MenuListDTO dto = new MenuListDTO();
        dto.setId(menuList.getId());
        dto.setName(menuList.getName());
        dto.setDescription(menuList.getDescription());
        dto.setPrice(menuList.getPrice());
        dto.setImage(menuList.getImage()); // Map image field
        dto.setRestaurantId(menuList.getRestaurant() != null ? menuList.getRestaurant().getId() : null);
        return dto;
    }

    public Restaurant toRestaurant(RestaurantDTO dto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(dto.getId());
        restaurant.setName(dto.getName());
        restaurant.setImage(dto.getImage()); // Map image field
        return restaurant;
    }

    public RestaurantDTO toRestaurantDTO(Restaurant restaurant) {
        RestaurantDTO dto = new RestaurantDTO();
        dto.setId(restaurant.getId());
        dto.setName(restaurant.getName());
        dto.setImage(restaurant.getImage()); // Map image field
        return dto;
    }

    public OrderList toOrderList(OrderListDTO dto) {
        OrderList orderList = new OrderList();
        orderList.setId(dto.getId());
        orderList.setQuantity(dto.getQuantity());
        orderList.setImage(dto.getImage());
        orderList.setLocation(dto.getLocation());
        orderList.setUsername(dto.getUsername());
        orderList.setPhone(dto.getPhone());
        orderList.setItemName(dto.getItemName());
        orderList.setDescription(dto.getDescription());
        orderList.setTotalAmount(dto.getTotalAmount());

        if (dto.getRestaurantId() != null) {
            Restaurant restaurant = restaurantService.getRestaurantById(dto.getRestaurantId())
                    .orElseThrow(() -> new IllegalArgumentException("Restaurant with ID " + dto.getRestaurantId() + " not found"));
            orderList.setRestaurant(restaurant);
        }

        if (dto.getMenuId() != null) {
            MenuList menuList = menuListService.getMenuById(dto.getMenuId())
                    .orElseThrow(() -> new IllegalArgumentException("Menu item with ID " + dto.getMenuId() + " not found"));
            orderList.setMenuList(menuList);
        }

        return orderList;
    }

    public OrderListDTO toOrderListDTO(OrderList orderList) {
        OrderListDTO dto = new OrderListDTO();
        dto.setId(orderList.getId());
        dto.setQuantity(orderList.getQuantity());
        dto.setImage(orderList.getImage());
        dto.setLocation(orderList.getLocation());
        dto.setUsername(orderList.getUsername());
        dto.setItemName(orderList.getItemName());
        dto.setPhone(orderList.getPhone());
        dto.setDescription(orderList.getDescription());
        dto.setTotalAmount(orderList.getTotalAmount());
        dto.setRestaurantId(orderList.getRestaurant() != null ? orderList.getRestaurant().getId() : null);
        dto.setMenuId(orderList.getMenuList() != null ? orderList.getMenuList().getId() : null);
        return dto;
    }

    public CartDto toCartDto(Cart cart) {
        CartDto dto = new CartDto();
        dto.setId(cart.getId());
        dto.setQuantity(cart.getQuantity());
        dto.setUserId(cart.getUserId());
        dto.setMenuListId(cart.getMenuList().getId());
        return dto;
    }

    public Cart toCart(CartDto dto) {
        Cart cart = new Cart();
        cart.setId(dto.getId());
        cart.setUserId(dto.getUserId());
        cart.setQuantity(dto.getQuantity());

        MenuList menuList = menuListService.getMenuById(dto.getMenuListId())
                .orElseThrow(() -> new EntityNotFoundException("MenuList not found with ID: " + dto.getMenuListId()));
        cart.setMenuList(menuList);
        return cart;
    }
}

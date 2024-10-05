package com.koala.ai.FoodOrderApps.controllers;

import com.koala.ai.FoodOrderApps.dtos.CartDto;
import com.koala.ai.FoodOrderApps.dtos.OrderListDTO;
import com.koala.ai.FoodOrderApps.entities.Cart;
import com.koala.ai.FoodOrderApps.entities.OrderList;
import com.koala.ai.FoodOrderApps.exceptions.OrderNotFoundException;
import com.koala.ai.FoodOrderApps.mapper.MapperConfig;
import com.koala.ai.FoodOrderApps.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService service;
    private final MapperConfig config;

    @Autowired
    public CartController(MapperConfig config, CartService service) {
        this.config = config;
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<CartDto> createOrder(@RequestBody CartDto dto) {
        Cart cart = config.toCart(dto);
        Cart createdCart = service.saveCart(cart); // Renamed to saveOrder
        CartDto cartDto = config.toCartDto(createdCart);
        return new ResponseEntity<>(cartDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable("id") Long id) {
        try {
            service.deleteOrder(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/menu/{menuId}")
    public ResponseEntity<List<CartDto>> getCartsByMenuId(@PathVariable String menuId) {
        List<Cart> carts = service.getCartById(menuId); // Assuming you want to retrieve carts by menuId
        if (carts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<CartDto> dto = carts.stream()
                .map(config::toCartDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


}

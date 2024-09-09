package com.koala.ai.FoodOrderApps.controllers;

import com.koala.ai.FoodOrderApps.dtos.RestaurantDTO;
import com.koala.ai.FoodOrderApps.entities.Restaurant;
import com.koala.ai.FoodOrderApps.services.RestaurantService;
import com.koala.ai.FoodOrderApps.mapper.MapperConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService service;
    private final MapperConfig mapperConfig;

    @Autowired
    public RestaurantController(RestaurantService service, MapperConfig mapperConfig) {
        this.service = service;
        this.mapperConfig = mapperConfig;
    }

    @PostMapping
    public ResponseEntity<RestaurantDTO> createRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        Restaurant restaurant = mapperConfig.toRestaurant(restaurantDTO);
        Restaurant createdRestaurant = service.saveRestaurant(restaurant);
        RestaurantDTO createdRestaurantDTO = mapperConfig.toRestaurantDTO(createdRestaurant);
        return new ResponseEntity<>(createdRestaurantDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants() {
        List<Restaurant> restaurants = service.getAllRestaurants();
        List<RestaurantDTO> restaurantDTOs = restaurants.stream()
                .map(mapperConfig::toRestaurantDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(restaurantDTOs, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable String id) {
        boolean isDeleted = service.deleteRestaurant(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


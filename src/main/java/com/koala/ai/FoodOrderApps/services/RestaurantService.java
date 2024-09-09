package com.koala.ai.FoodOrderApps.services;

import com.koala.ai.FoodOrderApps.entities.Restaurant;
import com.koala.ai.FoodOrderApps.repositories.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    private final RestaurantRepo repository;

    @Autowired
    public RestaurantService(RestaurantRepo repository) {
        this.repository = repository;
    }

    @Transactional
    public Restaurant saveRestaurant(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    public List<Restaurant> getAllRestaurants() {
        return repository.findAll();
    }

    public Optional<Restaurant> getRestaurantById(String id) {
        return repository.findById(id);
    }

    @Transactional
    public boolean deleteRestaurant(String id) {
        try {
            if (repository.existsById(id)) {
                repository.deleteById(id);
                return true;
            }
            return false;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}

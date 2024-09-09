package com.koala.ai.FoodOrderApps.repositories;

import com.koala.ai.FoodOrderApps.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant, String> {
}

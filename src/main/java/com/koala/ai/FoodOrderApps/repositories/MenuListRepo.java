package com.koala.ai.FoodOrderApps.repositories;

import com.koala.ai.FoodOrderApps.entities.MenuList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MenuListRepo extends JpaRepository<MenuList,Long> {
    List<MenuList> findByRestaurantId(String restaurantId);
}

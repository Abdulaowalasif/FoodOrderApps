package com.koala.ai.FoodOrderApps.repositories;

import com.koala.ai.FoodOrderApps.entities.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderListRepo extends JpaRepository<OrderList,Long> {
    List<OrderList> findByRestaurantId(String restaurantId);
}

package com.koala.ai.FoodOrderApps.repositories;


import com.koala.ai.FoodOrderApps.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {
    List<Cart> findByUserId (String userId);
}

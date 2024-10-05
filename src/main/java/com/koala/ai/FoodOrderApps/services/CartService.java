package com.koala.ai.FoodOrderApps.services;


import com.koala.ai.FoodOrderApps.entities.Cart;
import com.koala.ai.FoodOrderApps.entities.OrderList;
import com.koala.ai.FoodOrderApps.exceptions.OrderNotFoundException;
import com.koala.ai.FoodOrderApps.repositories.CartRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepo cartRepo;

    @Autowired
    public CartService(CartRepo cartRepo) {
        this.cartRepo = cartRepo;
    }

    @Transactional
    public Cart saveCart(Cart cart) {
        return cartRepo.save(cart);
    }

    @Transactional
    public boolean deleteOrder(Long id) {
        if (!cartRepo.existsById(id)) {
            throw new OrderNotFoundException("Order with ID " + id + " not found");
        }
        cartRepo.deleteById(id);
        return true; // Return true if the deletion was successful
    }

    public List<Cart> getCartById(String uid) {
        return cartRepo.findByUserId(uid);
    }

    public Cart getCartById(Long id) {
        return cartRepo.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order with ID " + id + " not found"));
    }
}

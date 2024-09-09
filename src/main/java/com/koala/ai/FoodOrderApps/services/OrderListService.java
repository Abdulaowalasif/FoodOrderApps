package com.koala.ai.FoodOrderApps.services;

import com.koala.ai.FoodOrderApps.entities.OrderList;
import com.koala.ai.FoodOrderApps.exceptions.OrderNotFoundException;
import com.koala.ai.FoodOrderApps.repositories.OrderListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderListService {

    private final OrderListRepo orderListRepository;

    @Autowired
    public OrderListService(OrderListRepo orderListRepository) {
        this.orderListRepository = orderListRepository;
    }
    @Transactional
    public OrderList saveOrder(OrderList order) {
        // Optionally validate the order object here
        return orderListRepository.save(order);
    }
    public List<OrderList> getAllOrders() {
        return orderListRepository.findAll();
    }

    @Transactional
    public boolean deleteOrder(Long id) {
        if (!orderListRepository.existsById(id)) {
            throw new OrderNotFoundException("Order with ID " + id + " not found");
        }
        orderListRepository.deleteById(id);
        return true; // Return true if the deletion was successful
    }

    public OrderList getOrderById(Long id) {
        return orderListRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order with ID " + id + " not found"));
    }
    public List<OrderList> getOrdersByRestaurantId(String restaurantId) {
        return orderListRepository.findByRestaurantId(restaurantId);
    }
}

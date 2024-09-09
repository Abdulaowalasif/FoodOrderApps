package com.koala.ai.FoodOrderApps.controllers;

import com.koala.ai.FoodOrderApps.dtos.OrderListDTO;
import com.koala.ai.FoodOrderApps.entities.OrderList;
import com.koala.ai.FoodOrderApps.exceptions.OrderNotFoundException;
import com.koala.ai.FoodOrderApps.mapper.MapperConfig;
import com.koala.ai.FoodOrderApps.services.OrderListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderListController {

    private final OrderListService service;
    private final MapperConfig mapperConfig;

    @Autowired
    public OrderListController(OrderListService service, MapperConfig mapperConfig) {
        this.service = service;
        this.mapperConfig = mapperConfig;
    }

    @PostMapping
    public ResponseEntity<OrderListDTO> createOrder(@RequestBody OrderListDTO orderListDTO) {
        OrderList orderList = mapperConfig.toOrderList(orderListDTO);
        OrderList createdOrder = service.saveOrder(orderList); // Renamed to saveOrder
        OrderListDTO createdOrderDTO = mapperConfig.toOrderListDTO(createdOrder);
        return new ResponseEntity<>(createdOrderDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrderListDTO>> getAllOrders() {
        List<OrderList> orders = service.getAllOrders(); // Renamed to getAllOrders
        List<OrderListDTO> orderListDTOs = orders.stream()
                .map(mapperConfig::toOrderListDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(orderListDTOs, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Long id) {
        try {
            service.deleteOrder(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<OrderListDTO>> getOrdersByRestaurantId(@PathVariable String restaurantId) {
        List<OrderList> orders = service.getOrdersByRestaurantId(restaurantId);
        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<OrderListDTO> orderListDTOs = orders.stream()
                .map(mapperConfig::toOrderListDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(orderListDTOs, HttpStatus.OK);
    }
}

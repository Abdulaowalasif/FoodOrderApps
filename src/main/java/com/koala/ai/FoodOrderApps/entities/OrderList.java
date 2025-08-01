package com.koala.ai.FoodOrderApps.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_list")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private MenuList menuList;

    @Column(name = "image")
    private String image;

    @Column(name = "itemName")
    private String itemName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "description")
    private String description;

    @Column(name = "username")
    private String username;

    @Column(name = "location")
    private String location;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "total_amount")
    private Double totalAmount;
}

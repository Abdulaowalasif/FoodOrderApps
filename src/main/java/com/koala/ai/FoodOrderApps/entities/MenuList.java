package com.koala.ai.FoodOrderApps.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menu_list")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MenuList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @OneToMany(mappedBy = "menuList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderList> orderLists = new ArrayList<>();

    @OneToMany(mappedBy = "menuList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> cart = new ArrayList<>();

}

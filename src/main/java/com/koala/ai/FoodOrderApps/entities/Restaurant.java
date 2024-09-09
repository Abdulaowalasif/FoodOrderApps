package com.koala.ai.FoodOrderApps.entities;

import com.koala.ai.FoodOrderApps.entities.MenuList;
import com.koala.ai.FoodOrderApps.entities.OrderList;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurant")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Restaurant {

    @Id
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "image") // Add this field to store image URL or path
    private String image;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuList> menuLists = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderList> orderLists = new ArrayList<>();


}

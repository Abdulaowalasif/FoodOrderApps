package com.koala.ai.FoodOrderApps.entities;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "Cart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_list_id", nullable = false)
    private MenuList menuList;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "user_id", nullable = false)
    private String userId;

}

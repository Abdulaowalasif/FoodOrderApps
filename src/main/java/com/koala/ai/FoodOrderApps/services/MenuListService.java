package com.koala.ai.FoodOrderApps.services;

import com.koala.ai.FoodOrderApps.entities.MenuList;
import com.koala.ai.FoodOrderApps.exceptions.MenuNotFoundException;
import com.koala.ai.FoodOrderApps.repositories.MenuListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MenuListService {

    private final MenuListRepo menuListRepository;

    @Autowired
    public MenuListService(MenuListRepo menuListRepository) {
        this.menuListRepository = menuListRepository;
    }

    @Transactional
    public MenuList saveMenu(MenuList menu) {
        // Optionally validate the menu object here
        return menuListRepository.save(menu);
    }

    public List<MenuList> getAllMenu() {
        return menuListRepository.findAll();
    }

    @Transactional
    public void deleteMenu(Long id) {
        if (!menuListRepository.existsById(id)) {
            throw new MenuNotFoundException("Menu item with ID " + id + " not found");
        }
        menuListRepository.deleteById(id);
    }

    public Optional<MenuList> getMenuById(Long id) {
        return menuListRepository.findById(id);
    }

    public List<MenuList> getMenuByRestaurantId(String restaurantId) {
        return menuListRepository.findByRestaurantId(restaurantId);
    }

    public List<MenuList> searchMenuByName(String name) {
        return menuListRepository.findByNameContainingIgnoreCase(name);
    }

}

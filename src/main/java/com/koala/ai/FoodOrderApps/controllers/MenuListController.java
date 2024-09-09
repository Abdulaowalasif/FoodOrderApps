package com.koala.ai.FoodOrderApps.controllers;

import com.koala.ai.FoodOrderApps.dtos.MenuListDTO;
import com.koala.ai.FoodOrderApps.entities.MenuList;
import com.koala.ai.FoodOrderApps.exceptions.MenuNotFoundException;
import com.koala.ai.FoodOrderApps.mapper.MapperConfig;
import com.koala.ai.FoodOrderApps.services.MenuListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/menus")
public class MenuListController {

    private final MenuListService service;
    private final MapperConfig mapperConfig;

    @Autowired
    public MenuListController(MenuListService service, MapperConfig mapperConfig) {
        this.service = service;
        this.mapperConfig = mapperConfig;
    }

    @PostMapping
    public ResponseEntity<MenuListDTO> addMenu(@RequestBody MenuListDTO menuListDTO) {
        try {
            if (menuListDTO.getRestaurantId() == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Handle missing restaurantId
            }

            MenuList menuList = mapperConfig.toMenuList(menuListDTO);
            MenuList createdMenu = service.saveMenu(menuList);
            MenuListDTO createdMenuDTO = mapperConfig.toMenuListDTO(createdMenu);
            return new ResponseEntity<>(createdMenuDTO, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Handle invalid input
        } catch (Exception e) {
            e.printStackTrace(); // Log detailed error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable("id") Long menuId) {
        try {
            service.deleteMenu(menuId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (MenuNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<MenuListDTO>> getAllMenu() {
        List<MenuList> menus = service.getAllMenu();
        if (menus.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<MenuListDTO> menuListDTOs = menus.stream()
                .map(mapperConfig::toMenuListDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(menuListDTOs, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<MenuListDTO>> getMenuByRestaurantId(@PathVariable String restaurantId) {
        List<MenuList> menuLists = service.getMenuByRestaurantId(restaurantId);
        if (menuLists.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<MenuListDTO> menuListDTOs = menuLists.stream()
                .map(mapperConfig::toMenuListDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(menuListDTOs, HttpStatus.OK);
    }
}

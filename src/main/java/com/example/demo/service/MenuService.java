package com.example.demo.service;

import com.example.demo.dto.MenuDTO;
import com.example.demo.entity.Menu;
import com.example.demo.entity.Restaurant;
import com.example.demo.repository.MenuRepository;
import com.example.demo.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    MenuRepository repository;
    @Autowired
    RestaurantRepository restaurantRepository;

    public Menu insertIntoDB(MenuDTO menuDTO) {
        Optional<Restaurant> optional = restaurantRepository.findById(menuDTO.getRestaurant());

        if (optional.isPresent()) {
            Menu menu = Menu.create(menuDTO);
            menu.setRestaurant(optional.get());
            return repository.save(menu);
        } else {
            return null;
        }
    }

    public Menu updateClient(Menu menu) {
        Optional<Menu> newMenu = repository.findById(menu.getId());
        if (newMenu.isPresent()) {
            return repository.save(menu);
        } else {
            return null;
        }
    }

    public boolean delete(Long id) {
        Optional<Menu> menu = repository.findById(id);
        if (menu.isPresent()) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Optional<Menu> findById(Long id) {
        return repository.findById(id);
    }
}

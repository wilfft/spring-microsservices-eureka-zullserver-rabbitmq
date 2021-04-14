package com.example.demo.service;

import com.example.demo.entity.Restaurant;
import com.example.demo.entity.Restaurant;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestauranteService {

    @Autowired
    RestaurantRepository repository;


    public Restaurant insertIntoDB(Restaurant restaurant) {
        return repository.save(restaurant);
    }


    public Restaurant updateClient(Restaurant restaurant) {
        Optional<Restaurant> newRestaurant = repository.findById(restaurant.getId());
        if (newRestaurant.isPresent()) {
            return repository.save(restaurant);
        } else {
            return null;
        }
    }

    public boolean delete(Long id) {
        Optional<Restaurant> restaurant = repository.findById(id);
        if (restaurant.isPresent()) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Optional<Restaurant> findById(Long id) {
        return repository.findById(id);
    }
}

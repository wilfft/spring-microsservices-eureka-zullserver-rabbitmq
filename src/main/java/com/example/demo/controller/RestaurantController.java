package com.example.demo.controller;


import com.example.demo.dto.ClientDTO;
import com.example.demo.dto.RestaurantDTO;
import com.example.demo.entity.Client;
import com.example.demo.entity.Restaurant;
import com.example.demo.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {


    @Autowired
    RestauranteService service;


    @PostMapping("/insert")
    public ResponseEntity createBy(@RequestBody RestaurantDTO restaurantDTO) {
        try {
            return ResponseEntity.ok(service.insertIntoDB(Restaurant.create(restaurantDTO)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity clientUpdate(@PathVariable Long id, @RequestBody RestaurantDTO restaurantDTO) {

        Restaurant restaurant  = Restaurant.create(restaurantDTO);
        restaurant.setId(id);
        Restaurant updated = service.updateClient(restaurant);

        return Objects.nonNull
                (updated) ?
                ResponseEntity.ok(updated) :
                ResponseEntity.notFound().build();
     /*   if (updatedClient != null) {
            return ResponseEntity.ok(updatedClient);
        } else {
            return ResponseEntity.notFound().build();
        }*/
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        return service.delete(id) ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        Optional<Restaurant> restaurant = service.findById(id);

        return restaurant.isPresent() ?
                ResponseEntity.ok(restaurant.get()) :
                ResponseEntity.notFound().build();
    }

}

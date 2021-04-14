package com.example.demo.controller;


import com.example.demo.dto.MenuDTO;
import com.example.demo.entity.Menu;
import com.example.demo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuService service;

    @PostMapping("/insert")
    public ResponseEntity createBy(@RequestBody MenuDTO menuDTO) {
        try {
            Menu menu = service.insertIntoDB(menuDTO);
            return Objects.nonNull(menu) ?
                    ResponseEntity.ok(menu) :
                    ResponseEntity.notFound().build();

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody MenuDTO menuDTO) {

        Menu restaurant = Menu.create(menuDTO);
        restaurant.setId(id);
        Menu updated = service.updateClient(restaurant);

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
        Optional<Menu> menu = service.findById(id);

        return menu.isPresent() ?
                ResponseEntity.ok(menu.get()) :
                ResponseEntity.notFound().build();
    }
}

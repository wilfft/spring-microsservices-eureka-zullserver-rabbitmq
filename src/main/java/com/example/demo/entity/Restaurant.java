package com.example.demo.entity;

import com.example.demo.dto.RestaurantDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.*;

@Entity
@Table(name = "tb_restaurante")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String password;
    private String email;

    public static Restaurant create(RestaurantDTO restaurantDTO) {
        return new ModelMapper().map(restaurantDTO, Restaurant.class);
    }
}

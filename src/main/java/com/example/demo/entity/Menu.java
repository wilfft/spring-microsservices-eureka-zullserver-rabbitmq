package com.example.demo.entity;

import com.example.demo.dto.ClientDTO;
import com.example.demo.dto.MenuDTO;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String price;

    @ManyToOne(fetch = FetchType.LAZY) //so busca no banco se eu der GET
    private Restaurant restaurant;

    public static Menu create(MenuDTO menuDTO) {
        return new ModelMapper().map(menuDTO, Menu.class);
    }
}

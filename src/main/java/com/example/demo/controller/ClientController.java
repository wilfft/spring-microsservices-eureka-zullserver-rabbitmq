package com.example.demo.controller;


import com.example.demo.dto.ClientDTO;
import com.example.demo.entity.Client;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {


    @Autowired
    ClientService service;


    @PostMapping("/insert")
    public ResponseEntity createBy(@RequestBody ClientDTO clienteDTO) {
        try {
            return ResponseEntity.ok(service.insertIntoDB(Client.create(clienteDTO)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity clientUpdate(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {

        Client client = Client.create(clientDTO);
        client.setId(id);
        Client updatedClient = service.updateClient(client);

        return Objects.nonNull
                (updatedClient) ?
                ResponseEntity.ok(updatedClient) :
                ResponseEntity.notFound().build();
     /*   if (updatedClient != null) {
            return ResponseEntity.ok(updatedClient);
        } else {
            return ResponseEntity.notFound().build();
        }*/
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletClient(@PathVariable Long id) {

        return service.deleteClient(id) ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        Optional<Client> cliente = service.findById(id);

        return cliente.isPresent() ?
                ResponseEntity.ok(cliente.get()) :
                ResponseEntity.notFound().build();
    }

}

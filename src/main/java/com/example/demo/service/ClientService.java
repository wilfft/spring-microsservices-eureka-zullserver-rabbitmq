package com.example.demo.service;

import com.example.demo.entity.Client;
import com.example.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClienteRepository repository;


    public Client insertIntoDB(Client client) {
        return repository.save(client);
    }

    public Client updateClient(Client client) {
        Optional<Client> newClient = repository.findById(client.getId());
        if (newClient.isPresent()) {
            return repository.save(client);
        } else {
            return null;
        }
    }

    public boolean deleteClient(Long id) {
        Optional<Client> client = repository.findById(id);
        if (client.isPresent()) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Optional<Client> findById(Long id) {
        return repository.findById(id);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delivery.app.Clients;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Service
public class ClientsServiceImpl implements ClientsService{
   @Autowired
    private ClientsRepository clientsRepo;

    @Override
    @Transactional
    public List<Clients> ClientsList() {
        return(List<Clients>) clientsRepo.findAll();
    }

    @Override
    @Transactional
    public void CreateClient(Clients client) {
        clientsRepo.save(client);
    }   

    @Override
    public void DeleteClient(long id) {
    Clients toDelete = clientsRepo.findById(id).orElseThrow(() -> new NoSuchElementException("No client with ID " + id));
    clientsRepo.delete(toDelete);
    }

    @Override
    public Clients FindClient(long id) {
       return clientsRepo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void UpdateClient(long id,Clients client) {
  Optional<Clients> existingClient = clientsRepo.findById(id);

    if (existingClient.isPresent()) {
        Clients managedClient = existingClient.get();
        managedClient.setName(client.getName());
        managedClient.setLastName(client.getLastName());
        managedClient.setEmail(client.getEmail());
        managedClient.setPassword(client.getPassword());
        managedClient.setPhone(client.getPhone());
        
        clientsRepo.save(managedClient);
    }
    
    }
}

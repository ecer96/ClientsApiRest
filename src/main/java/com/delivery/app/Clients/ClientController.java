/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delivery.app.Clients;

import java.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Admin
 */
@RestController
@Slf4j
public class ClientController {

    private ClientsServiceImpl clientService;

    @Autowired
    public ClientController(ClientsServiceImpl clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/Clients")
    public ResponseEntity<?> ClientsList() {
        try {
            List<Clients> clients = clientService.ClientsList();
            if (clients.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are No Clients");
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(clients);
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong,Error:" + e);
        }
    }

    @GetMapping("/Client/{id}")
    public ResponseEntity<?> GetClient(@PathVariable long id) {
        try {
            Clients client = clientService.FindClient(id);
            if (client == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(client);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @PostMapping("/CreateClient")
    public ResponseEntity<String> CreateClient(@RequestBody Clients client) {
        try {

            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPass = passwordEncoder.encode(client.getPassword());
            client.setPassword(hashedPass);

            clientService.CreateClient(client);
            return ResponseEntity.ok("Cliente creado exitosamente" + client);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo crear el cliente. Error: " + e.getMessage());
        }

    }

    @PutMapping("/EditClient/{id}")
    public ResponseEntity<?> EditClient(@PathVariable long id, @RequestBody Clients client) {
        try {
            Clients clientEdit = clientService.FindClient(id);

            if (clientEdit == null) {
                return ResponseEntity.notFound().build();
            } else {
                if (client.getName() != null && !client.getName().isEmpty()) {
                    clientEdit.setName(client.getName());
                }

                if (client.getLastName() != null && !client.getLastName().isEmpty()) {
                    clientEdit.setLastName(client.getLastName());
                }

                if (client.getEmail() != null && !client.getEmail().isEmpty()) {
                    clientEdit.setEmail(client.getEmail());
                }

                if (client.getPassword() != null && !client.getPassword().isEmpty()) {
                    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                    String hashedPass = passwordEncoder.encode(client.getPassword());
                    client.setPassword(hashedPass);
                    clientEdit.setPassword(client.getPassword());
                }

                if (client.getPhone() != null && !client.getPhone().isEmpty()) {
                    clientEdit.setPhone(client.getPhone());
                }

                clientService.UpdateClient(id, client);

                return ResponseEntity.ok(clientEdit);
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong" + e);
        }
    }

    @DeleteMapping("/DeleteClient/{id}")
    public ResponseEntity<String> DeleteClient(@PathVariable long id) {
        try {
            clientService.DeleteClient(id);
            return ResponseEntity.ok("Cliente eliminado correctamente");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

}

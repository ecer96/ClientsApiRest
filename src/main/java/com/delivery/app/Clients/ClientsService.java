/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delivery.app.Clients;

import java.util.List;

/**
 *
 * @author Admin
 */

public interface ClientsService{
    
    public List<Clients> ClientsList();
    
     public void CreateClient(Clients client);
    
    public void DeleteClient(long id);
    
    public void UpdateClient(long id,Clients client);
    
    public Clients FindClient(long id);
}

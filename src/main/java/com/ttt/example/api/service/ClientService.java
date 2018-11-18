package com.ttt.example.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttt.example.api.exception.NotFoundException;
import com.ttt.example.api.model.Client;
import com.ttt.example.api.repository.ClientRepository;

@Service
public class ClientService
{
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getClients(){
        return clientRepository.findAll();
    }

    public Client getClientById(Integer clientId) {
        return clientRepository.findClientById(clientId).orElseThrow(NotFoundException::new);
    }
}

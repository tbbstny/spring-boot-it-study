package com.ttt.example.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ttt.example.api.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    List<Client> findAll();
    Optional<Client> findClientById(Integer clientId);
}

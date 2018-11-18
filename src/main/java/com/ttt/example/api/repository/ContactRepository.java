package com.ttt.example.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ttt.example.api.model.Client;
import com.ttt.example.api.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {
    Contact findById(Integer id);
    List<Contact> findByClient(Client client);
}

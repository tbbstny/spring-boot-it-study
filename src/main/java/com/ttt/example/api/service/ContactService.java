package com.ttt.example.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttt.example.api.model.Client;
import com.ttt.example.api.model.Contact;
import com.ttt.example.api.repository.ContactRepository;

@Service
public class ContactService
{
    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> findByClient(Client client) {
        return contactRepository.findByClient(client);
    }

    public Contact createContact(Contact newContact) {
        contactRepository.save(newContact);
        return contactRepository.findById(newContact.getId());
    }

    public Contact updateContact(Integer contactId, Contact newContact) {
        contactRepository.save(newContact);
        return contactRepository.findById(contactId);
    }

    public Contact deleteContact(Integer contactId) {
        Contact deleteContact = contactRepository.findById(contactId);
        contactRepository.delete(deleteContact);
        return contactRepository.findById(contactId);
    }
}

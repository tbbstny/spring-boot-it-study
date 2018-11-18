package com.ttt.example.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.ttt.example.api.model.Client;
import com.ttt.example.api.model.Contact;
import com.ttt.example.api.service.ClientService;
import com.ttt.example.api.service.ContactService;

@RestController
@RequestMapping(path = "/", produces = "application/json")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private ClientService clientService;

    @GetMapping("/api/v1/clients/{id}/contacts")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<Contact>> get(@PathVariable("id") Integer clientId) {
        Client client = clientService.getClientById(clientId);
        List<Contact> contacts = contactService.findByClient(client);
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @PostMapping("/api/v1/clients/{id}/contacts")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Contact> create(
            @RequestParam Map<String, String> allRequestParams,
            @RequestBody Contact newContact) {
        return new ResponseEntity<>(contactService.createContact(newContact), HttpStatus.OK);
    }

    @PutMapping("/api/v1/contacts/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Contact> update(
            @PathVariable("id") Integer contactId,
            @RequestParam Map<String, String> allRequestParams,
            @RequestBody Contact newContact) {
        return new ResponseEntity<>(contactService.updateContact(contactId, newContact), HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/contacts/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Contact> delete(
            @PathVariable("id") Integer contactId,
            @RequestParam Map<String, String> allRequestParams) {
        return new ResponseEntity<>(contactService.deleteContact(contactId), HttpStatus.OK);
    }
}

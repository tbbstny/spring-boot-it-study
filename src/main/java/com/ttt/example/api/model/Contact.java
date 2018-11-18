package com.ttt.example.api.model;

import javax.persistence.*;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Contact")
public class Contact extends AuditModel
{
    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phoneNumber1")
    private String phoneNumber1;

    @Column(name = "phoneNumber2")
    private String phoneNumber2;

    @Column(name = "status")
    private String status;
}

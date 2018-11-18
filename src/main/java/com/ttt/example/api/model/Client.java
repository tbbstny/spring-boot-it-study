package com.ttt.example.api.model;

import javax.persistence.*;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Client")
public class Client extends AuditModel
{
    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "notes")
    private String notes;
}

package com.ttt.example.api.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditModel implements Serializable
{
    @CreatedBy
    @Column(name = "addedById")
    private String addedById;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "addedDate")
    private Date addedDate;

    @LastModifiedBy
    @Column(name = "lastModifiedById")
    private String lastModifiedById;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastModifiedDate")
    private Date lastModifiedDate;
}

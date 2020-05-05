package com.axa.softwareacademy.p6.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Payment {
    @Id
    int id;
    @OneToOne
    Account accountTransmitter;
    @OneToOne
    Account accountRecipient;
    float sum;
    String status;
    String type;
    Date paymentDate;
    @OneToOne
    Commission paymentCommission;
}

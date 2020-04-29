package com.axa.softwareacademy.p6.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Payment {
    @Id
    int id;
    @ManyToOne
    Account accountTransmitter;
    @ManyToOne
    Account accountRecipient;
    float sum;
    String status;
    String type;
    Date paymentDate;
    @ManyToOne
    Commission paymentCommission;
}

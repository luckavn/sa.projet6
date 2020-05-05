package com.axa.softwareacademy.p6.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
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

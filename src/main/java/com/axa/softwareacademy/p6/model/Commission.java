package com.axa.softwareacademy.p6.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Commission {
    @Id
    int id;
    Float commissionAmount;
}

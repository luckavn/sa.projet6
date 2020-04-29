package com.axa.softwareacademy.p6.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class AppRevenue {
    @Id
    int id;
    @OneToMany
    List<Commission> commissionList;
    float totalRevenue;
}

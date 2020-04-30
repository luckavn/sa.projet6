package com.axa.softwareacademy.p6.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
//@Table(name="app_revenue")
public class AppRevenue {
    @Id
    int id;
    @OneToMany
    List<Commission> commissionList;
    float totalRevenue;
}

package edu.uph.ii.ppproject.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "tenants")
public class Tenant extends User{
    @Id
    private Long id;
    @ManyToMany(mappedBy = "tenants")
    private List<Apartment> apartments;
}

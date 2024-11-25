package edu.uph.ii.ppproject.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tenants")
@Data
public class Tenant extends User{
    @ManyToMany(mappedBy = "tenants")
    private List<Apartment> apartments;
}

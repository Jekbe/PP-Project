package edu.uph.ii.ppproject.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "apartments")
@Data
public class Apartment {
    @Id
    private Long id;
    private int number;
    private double area;
    private int numberOfRooms;
    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;
    @ManyToMany
    private List<Tenant> tenants;
    @OneToMany(mappedBy = "apartment")
    private List<Fee> fees;
}
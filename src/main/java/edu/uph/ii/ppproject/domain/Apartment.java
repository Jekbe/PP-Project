package edu.uph.ii.ppproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity @Table(name = "apartments") @Getter @Setter @NoArgsConstructor
public class Apartment {
    @Id
    private Long apartmentId;
    private int number;
    private float area;
    private float price;
    private int numberOfRooms;
    @ManyToOne @JoinColumn(name = "building_id")
    private Building building;
    @ManyToMany
    private List<User> tenants;
    @OneToMany(mappedBy = "apartment")
    private List<Fee> fees;
    @ManyToMany
    private List<Utility> utilities;
}

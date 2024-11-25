package edu.uph.ii.ppproject.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Buildings")
@Data
public class Building {
    @Id
    private Long buildingId;
    private Address address;
    private int numberOfApartments;
    @ManyToOne
    @JoinColumn(name = "managerId")
    private Manager manager;
    @OneToMany(mappedBy = "building")
    private List<Apartment> apartments;
    @OneToMany(mappedBy = "building")
    private List<Event> events;
}

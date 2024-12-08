package edu.uph.ii.ppproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Buildings")
@Getter
@Setter
@NoArgsConstructor
public class Building {
    @Id
    private Long buildingId;
    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;
    @OneToMany(mappedBy = "building")
    private List<Apartment> apartments;
    private int numberOfApartments;
    @OneToMany(mappedBy = "building")
    private List<Event> events;
}

package edu.uph.ii.ppproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity @Table(name = "Buildings") @Getter @Setter @NoArgsConstructor
public class Building {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long buildingId;
    @Embedded
    private Address address;
    @ManyToOne @JoinColumn(name = "user_id")
    private User manager;
    @OneToMany(mappedBy = "building")
    private List<Apartment> apartments;
    @OneToMany(mappedBy = "building")
    private List<Event> events;
}

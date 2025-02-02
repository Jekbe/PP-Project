package edu.uph.ii.ppproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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
    private List<Apartment> apartments = new ArrayList<>();
    @OneToMany(mappedBy = "building")
    private List<Event> events = new ArrayList<>();

    public void addApartment(Apartment apartment) {
        this.apartments.add(apartment);
    }

    public void removeApartment(Apartment apartment) {
        this.apartments.remove(apartment);
    }

    public void addEvent(Event event) {
        this.events.add(event);
    }

    public void removeEvent(Event event) {
        this.events.remove(event);
    }
}

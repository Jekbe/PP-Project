package edu.uph.ii.ppproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "Utilities") @Getter @Setter @NoArgsConstructor
public class Utility {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long utilityId;
    private String name;
    private String description;
    private float price;
    @ManyToMany
    List<Apartment> apartments = new ArrayList<>();

    public void addApartment(Apartment apartment) {
        this.apartments.add(apartment);
    }

    public void removeApartment(Apartment apartment) {
        this.apartments.remove(apartment);
    }
}

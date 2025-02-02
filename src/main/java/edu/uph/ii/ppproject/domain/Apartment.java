package edu.uph.ii.ppproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "apartments") @Getter @Setter @NoArgsConstructor
public class Apartment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long apartmentId;
    private int number;
    private float area;
    private float price;
    private int numberOfRooms;
    @ManyToOne @JoinColumn(name = "building_id")
    private Building building;
    @ManyToMany
    private List<User> tenants = new ArrayList<>();
    @OneToMany(mappedBy = "apartment")
    private List<Fee> fees = new ArrayList<>();
    @ManyToMany
    private List<Utility> utilities = new ArrayList<>();

    public void addTenant(User user) {
        tenants.add(user);
    }

    public void removeTenant(User user) {
        tenants.remove(user);
    }

    public void addFee(Fee fee) {
        fees.add(fee);
    }

    public void removeFee(Fee fee) {
        fees.remove(fee);
    }

    public void addUtility(Utility utility) {
        utilities.add(utility);
    }

    public void removeUtility(Utility utility) {
        utilities.remove(utility);
    }
}

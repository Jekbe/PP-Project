package edu.uph.ii.ppproject.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable @Getter @Setter @NoArgsConstructor
public class Address {
    private String city;
    private String street;
    private String number;

    @Override
    public String toString(){
        return city + " " + street + " " + number;
    }
}

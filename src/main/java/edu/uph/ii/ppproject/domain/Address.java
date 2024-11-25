package edu.uph.ii.ppproject.domain;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Address {
    private String city;
    private String streat;
    private String number;
}

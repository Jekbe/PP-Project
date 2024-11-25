package edu.uph.ii.ppproject.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "fees")
@Data
public class Fee {
    @Id
    private Long id;
    private String Tittle;
    private double amount;
    private LocalDate maturity;
    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;
}

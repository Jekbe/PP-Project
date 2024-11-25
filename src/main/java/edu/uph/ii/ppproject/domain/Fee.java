package edu.uph.ii.ppproject.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "fees")
@Data
public class Fee {
    @Id
    private Long feeId;
    private String tittle;
    private float amount;
    private LocalDate maturity;
    @ManyToOne
    @JoinColumn(name = "apartmentId")
    private Apartment apartment;
}

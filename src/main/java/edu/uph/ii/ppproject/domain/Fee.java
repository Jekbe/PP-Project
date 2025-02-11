package edu.uph.ii.ppproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity @Table(name = "fees") @Getter @Setter @NoArgsConstructor
public class Fee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feeId;
    private String tittle;
    private float amount;
    private LocalDate maturity;
    @ManyToOne @JoinColumn(name = "apartment_id")
    private Apartment apartment;
    private Status status;

    public enum Status{
        OPLACONE,
        NIE_OPLACONE
    }
}

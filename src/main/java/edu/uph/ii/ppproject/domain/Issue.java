package edu.uph.ii.ppproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity @Table(name = "Issues") @Getter @Setter @NoArgsConstructor
public class Issue {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long issueId;
    private String descryption;
    private LocalDate date;
    private Status status;
    @ManyToOne
    private User tenant;

    public enum Status{
        NIEODCZYTANE,
        ODCZYTANE,
        W_TRAKCIE,
        ODRZUCONE,
        ZREALIZOWANE
    }
}

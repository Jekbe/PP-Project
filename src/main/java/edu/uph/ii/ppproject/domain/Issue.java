package edu.uph.ii.ppproject.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;



@Entity
@Table(name = "Issues")
@Data
public class Issue {
    public enum Status{
        Nieodczytane, Odczytane, W_trakcie, Odrzucone, Zrealizowane
    }

    @Id
    private Long issueId;
    private String descryption;
    private LocalDate date;
    private Status status;
    @ManyToOne
    private Tenant tenant;
}

package edu.uph.ii.ppproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "Utilities") @Getter @Setter @NoArgsConstructor
public class Utility {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long utilityId;
    private String type;
    private float price;
}

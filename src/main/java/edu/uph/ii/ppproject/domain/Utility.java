package edu.uph.ii.ppproject.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Utilities")
@Getter
@Setter
@NoArgsConstructor
public class Utility {
    @Id
    private Long utilityId;
    private String type;
    private float price;
}

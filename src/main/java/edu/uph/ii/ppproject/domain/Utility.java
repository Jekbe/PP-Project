package edu.uph.ii.ppproject.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Utilities")
@Data
public class Utility {
    @Id
    private Long utilityId;
    private String type;
    private float price;
}

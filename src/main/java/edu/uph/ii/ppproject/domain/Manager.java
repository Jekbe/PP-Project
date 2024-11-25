package edu.uph.ii.ppproject.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "managers")
@Data
public class Manager extends User{
    @Id
    private Long id;
    @OneToMany(mappedBy = "manager")
    private List<Building> buildings;
}

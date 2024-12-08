package edu.uph.ii.ppproject.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "managers")
@Getter
@Setter
@NoArgsConstructor
public class Manager extends User{
    @OneToMany(mappedBy = "manager")
    private List<Building> buildings;
}

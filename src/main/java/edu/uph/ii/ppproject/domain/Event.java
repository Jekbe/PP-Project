package edu.uph.ii.ppproject.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "events")
@Data
public class Event {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;
    private String message;
}

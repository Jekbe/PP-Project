package edu.uph.ii.ppproject.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "events")
@Data
public class Event {
    @Id
    private Long eventId;
    @ManyToOne
    @JoinColumn(name = "buildingId")
    private Building building;
    private String message;
}

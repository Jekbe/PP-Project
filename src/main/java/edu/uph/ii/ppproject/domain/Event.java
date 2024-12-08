package edu.uph.ii.ppproject.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
public class Event {
    @Id
    private Long eventId;
    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;
    private String message;
}

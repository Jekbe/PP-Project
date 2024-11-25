package edu.uph.ii.ppproject.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Entity
@Table
@Data
public class Notification {
    @Id
    private Long id;
    private String Tittle;
    private String content;
    @ManyToMany
    private List<User> users;
}

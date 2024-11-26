package edu.uph.ii.ppproject.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
@Data
public class User {
    @Id
    private Long userId;
    private String firstName;
    private String lastName;
    private String pesel;
    private String email;

}

package edu.uph.ii.ppproject.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    private Long userId;
    private String firstName;
    private String lastName;
    private String pesel;
    private String email;

    @Override
    public String toString(){
        return firstName + " " + lastName;
    }
}

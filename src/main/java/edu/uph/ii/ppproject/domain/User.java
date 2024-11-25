package edu.uph.ii.ppproject.domain;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public class User {
    private String firstName;
    private String lastName;
    private String pesel;
    private String email;
}

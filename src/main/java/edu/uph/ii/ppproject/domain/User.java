package edu.uph.ii.ppproject.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Set;

@Entity @Table(name = "users") @Getter @Setter @NoArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String lastName;
    private String pesel;
    //@Size(min = 4, max = 36) //@UniqueUsername
    private String email;
    //@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$")
    private String password;
    @Transient//nie będzie odwzorowana w db
    private String passwordConfirm;
    private boolean enabled = false;
    @ManyToMany(fetch = FetchType.EAGER) @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User(String email){
        this(email, false);
    }

    public User(String email, boolean enabled){
        this.email = email;
        this.enabled = enabled;
    }

    @AssertTrue
    private boolean isPasswordsEquals(){
        return password == null || passwordConfirm == null || password.equals(passwordConfirm);
    }

    @Override
    public String toString(){
        return firstName + " " + lastName;
    }
}

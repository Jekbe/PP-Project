package edu.uph.ii.ppproject.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import java.util.Set;
import java.util.UUID;

@Entity @Table(name = "users") @Getter @Setter @NoArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String lastName;
    private String pesel;
    private String email;
    private String password;
    private String activationCode = UUID.randomUUID().toString();
    @Transient
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

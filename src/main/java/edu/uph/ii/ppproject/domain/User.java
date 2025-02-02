package edu.uph.ii.ppproject.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity @Table(name = "users") @Getter @Setter @NoArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String lastName;
    private String pesel;
    private String email;
    private String password;
    @OneToMany
    private List<Document> myDocuments = new ArrayList<>();
    @OneToMany
    private List<Document> documentsToMe = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER) @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role) {
        roles.add(role);
    }

    public void removeRole(Role role) {
        roles.remove(role);
    }

    public void addMyDocument(Document document) {
        myDocuments.add(document);
    }

    public void removeMyDocument(Document document) {
        myDocuments.remove(document);
    }

    public void addDocumentsToMe(Document document) {
        documentsToMe.add(document);
    }

    public void removeDocumentsToMe(Document document) {
        documentsToMe.remove(document);
    }

    @Override
    public String toString(){
        return firstName + " " + lastName;
    }
}

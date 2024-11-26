package edu.uph.ii.ppproject.repositories;

import edu.uph.ii.ppproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

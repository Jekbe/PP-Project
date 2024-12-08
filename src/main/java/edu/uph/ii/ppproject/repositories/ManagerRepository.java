package edu.uph.ii.ppproject.repositories;

import edu.uph.ii.ppproject.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
}

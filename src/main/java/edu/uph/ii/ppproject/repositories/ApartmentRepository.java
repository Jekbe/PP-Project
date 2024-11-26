package edu.uph.ii.ppproject.repositories;

import edu.uph.ii.ppproject.domain.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
}

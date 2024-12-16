package edu.uph.ii.ppproject.repositories;

import edu.uph.ii.ppproject.domain.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    List<Apartment> findApartmentByBuilding_BuildingId(Long id);
}

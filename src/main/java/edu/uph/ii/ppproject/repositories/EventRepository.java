package edu.uph.ii.ppproject.repositories;

import edu.uph.ii.ppproject.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}

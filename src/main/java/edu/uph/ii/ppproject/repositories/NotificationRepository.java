package edu.uph.ii.ppproject.repositories;

import edu.uph.ii.ppproject.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}

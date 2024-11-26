package edu.uph.ii.ppproject.repositories;

import edu.uph.ii.ppproject.domain.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
}

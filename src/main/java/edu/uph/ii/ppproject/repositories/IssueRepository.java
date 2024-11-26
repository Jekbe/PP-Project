package edu.uph.ii.ppproject.repositories;

import edu.uph.ii.ppproject.domain.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long> {
}

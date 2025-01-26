package edu.uph.ii.ppproject.repositories;


import edu.uph.ii.ppproject.domain.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}

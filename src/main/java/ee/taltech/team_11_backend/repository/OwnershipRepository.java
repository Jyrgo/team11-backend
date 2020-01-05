package ee.taltech.team_11_backend.repository;

import ee.taltech.team_11_backend.model.Ownership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnershipRepository extends JpaRepository<Ownership, Long> {
}

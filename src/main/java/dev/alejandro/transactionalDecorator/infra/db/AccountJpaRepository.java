package dev.alejandro.transactionalDecorator.infra.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountJpaRepository extends JpaRepository<AccountJpa, UUID> {
    Optional<AccountJpa> findByClientId(UUID clientId);
    @Query(value = "SELECT * FROM accounts LIMIT :limit", nativeQuery = true)
    List<AccountJpa> listWithLimit(@RequestParam int limit);
}

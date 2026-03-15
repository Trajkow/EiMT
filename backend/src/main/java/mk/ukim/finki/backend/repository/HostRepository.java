package mk.ukim.finki.backend.repository;


import mk.ukim.finki.backend.model.enitites.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {
}

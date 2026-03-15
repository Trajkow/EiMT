package mk.ukim.finki.backend.service.domain;

import mk.ukim.finki.backend.model.enitites.Host;

import java.util.List;
import java.util.Optional;

public interface HostService {

    Optional<Host> findById(Long id);

    List<Host> findAll();

    Host create(Host host);

    Optional<Host> update(Long id, Host host);

    Optional<Host> deleteById(Long id);
}

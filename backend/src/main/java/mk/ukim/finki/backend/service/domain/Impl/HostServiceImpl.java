package mk.ukim.finki.backend.service.domain.Impl;

import mk.ukim.finki.backend.model.enitites.Host;
import mk.ukim.finki.backend.repository.HostRepository;
import mk.ukim.finki.backend.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {

    private final HostRepository hostRepository;

    public HostServiceImpl(HostRepository hostRepository){
        this.hostRepository = hostRepository;
    }

    @Override
    public Optional<Host> findById(Long id) {
        return this.hostRepository.findById(id);
    }

    @Override
    public List<Host> findAll() {
        return this.hostRepository.findAll();
    }

    @Override
    public Host create(Host host) {
        return this.hostRepository.save(host);
    }

    @Override
    public Optional<Host> update(Long id, Host host) {
        return this.hostRepository.findById(id)
                .map((current) ->{
                    current.setName(host.getName());
                    current.setSurname(host.getSurname());
                    return this.hostRepository.save(current);
                });
    }

    @Override
    public Optional<Host> deleteById(Long id) {
        Optional<Host> current = this.hostRepository.findById(id);
        current.ifPresent(this.hostRepository::delete);
        return current;
    }
}

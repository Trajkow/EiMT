package mk.ukim.finki.backend.service.domain.Impl;

import mk.ukim.finki.backend.model.enitites.Country;
import mk.ukim.finki.backend.repository.CountryRepository;
import mk.ukim.finki.backend.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);
    }

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Country create(Country country) {
        return this.countryRepository.save(country);
    }

    @Override
    public Optional<Country> update(Long id, Country country) {
        return this.countryRepository.findById(id)
                .map((current) ->{
                    current.setName(country.getName());
                    current.setContinent(country.getContinent());
                    return this.countryRepository.save(current);
                });
    }

    @Override
    public Optional<Country> deleteById(Long id) {
        Optional<Country> current = this.countryRepository.findById(id);
        current.ifPresent(this.countryRepository::delete);
        return current;
    }
}

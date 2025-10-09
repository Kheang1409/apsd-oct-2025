package asd.lab6.ads_dential_surgeries_mgmt_cli.repository.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import asd.lab6.ads_dential_surgeries_mgmt_cli.domain.Dentist;
import asd.lab6.ads_dential_surgeries_mgmt_cli.repository.DentistRepository;

@Profile("test")
@Repository
public class InMemoryDentistRepository implements DentistRepository {

    private final Map<Long, Dentist> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    @Override
    public Dentist save(Dentist dentist) {
        if (dentist.getId() == null) {
            dentist.setId(idGenerator.incrementAndGet());
        }
        storage.put(dentist.getId(), dentist);
        return dentist;
    }

    @Override
    public Optional<Dentist> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Dentist> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void deleteById(Long id) {
        storage.remove(id);
    }
}

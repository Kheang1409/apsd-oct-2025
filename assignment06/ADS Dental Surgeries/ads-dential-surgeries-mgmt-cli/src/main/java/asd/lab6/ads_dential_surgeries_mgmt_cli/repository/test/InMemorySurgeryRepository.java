package asd.lab6.ads_dential_surgeries_mgmt_cli.repository.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import asd.lab6.ads_dential_surgeries_mgmt_cli.domain.Surgery;
import asd.lab6.ads_dential_surgeries_mgmt_cli.repository.SurgeryRepository;

@Profile("test")
@Repository
public class InMemorySurgeryRepository implements SurgeryRepository {

    private final Map<Long, Surgery> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    @Override
    public Surgery save(Surgery surgery) {
        if (surgery.getId() == null) {
            surgery.setId(idGenerator.incrementAndGet());
        }
        storage.put(surgery.getId(), surgery);
        return surgery;
    }

    @Override
    public Optional<Surgery> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Surgery> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void deleteById(Long id) {
        storage.remove(id);
    }
}

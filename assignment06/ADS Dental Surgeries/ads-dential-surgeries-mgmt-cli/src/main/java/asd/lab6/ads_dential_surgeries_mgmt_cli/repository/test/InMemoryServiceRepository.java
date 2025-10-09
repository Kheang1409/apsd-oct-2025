package asd.lab6.ads_dential_surgeries_mgmt_cli.repository.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import asd.lab6.ads_dential_surgeries_mgmt_cli.domain.Service;
import asd.lab6.ads_dential_surgeries_mgmt_cli.repository.ServiceRepository;

@Profile("test")
@Repository
public class InMemoryServiceRepository implements ServiceRepository {

    private final Map<Long, Service> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    @Override
    public Service save(Service service) {
        if (service.getId() == null) {
            service.setId(idGenerator.incrementAndGet());
        }
        storage.put(service.getId(), service);
        return service;
    }

    @Override
    public Optional<Service> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Service> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void deleteById(Long id) {
        storage.remove(id);
    }
}

package asd.lab6.ads_dential_surgeries_mgmt_cli.repository.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import asd.lab6.ads_dential_surgeries_mgmt_cli.domain.Appointment;
import asd.lab6.ads_dential_surgeries_mgmt_cli.repository.AppointmentRepository;

@Profile("test")
@Repository
public class InMemoryAppointmentRepository implements AppointmentRepository {

    private final Map<Long, Appointment> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    @Override
    public Appointment save(Appointment appointment) {
        if (appointment.getId() == null) {
            appointment.setId(idGenerator.incrementAndGet());
        }
        storage.put(appointment.getId(), appointment);
        return appointment;
    }

    @Override
    public Optional<Appointment> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Appointment> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void deleteById(Long id) {
        storage.remove(id);
    }
}

package asd.lab6.ads_dential_surgeries_mgmt_cli.repository.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import asd.lab6.ads_dential_surgeries_mgmt_cli.domain.Address;
import asd.lab6.ads_dential_surgeries_mgmt_cli.repository.AddressRepository;

@Profile("test")
@Repository
public class InMemoryAddressRepository implements AddressRepository {

	private final Map<Long, Address> storage = new ConcurrentHashMap<>();
	private final AtomicLong idGenerator = new AtomicLong();

	@Override
	public Address save(Address address) {
		if (address.getId() == null) {
			address.setId(idGenerator.incrementAndGet());
		}
		storage.put(address.getId(), address);
		return address;
	}

	@Override
	public Optional<Address> findById(Long id) {
		return Optional.ofNullable(storage.get(id));
	}

	@Override
	public List<Address> findAll() {
		return new ArrayList<>(storage.values());
	}

	@Override
	public void deleteById(Long id) {
		storage.remove(id);
	}
}

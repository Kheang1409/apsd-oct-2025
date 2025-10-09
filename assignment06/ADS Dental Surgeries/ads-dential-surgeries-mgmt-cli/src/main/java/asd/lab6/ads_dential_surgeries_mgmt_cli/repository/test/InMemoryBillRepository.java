package asd.lab6.ads_dential_surgeries_mgmt_cli.repository.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import asd.lab6.ads_dential_surgeries_mgmt_cli.domain.Bill;
import asd.lab6.ads_dential_surgeries_mgmt_cli.repository.BillRepository;

@Profile("test")
@Repository
public class InMemoryBillRepository implements BillRepository {

	private final Map<Long, Bill> storage = new ConcurrentHashMap<>();
	private final AtomicLong idGenerator = new AtomicLong();

	@Override
	public Bill save(Bill bill) {
		if (bill.getId() == null) {
			bill.setId(idGenerator.incrementAndGet());
		}
		storage.put(bill.getId(), bill);
		return bill;
	}

	@Override
	public Optional<Bill> findById(Long id) {
		return Optional.ofNullable(storage.get(id));
	}

	@Override
	public List<Bill> findAll() {
		return new ArrayList<>(storage.values());
	}

	@Override
	public void deleteById(Long id) {
		storage.remove(id);
	}
}

package asd.lab6.ads_dential_surgeries_mgmt_cli.repository.prod;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import asd.lab6.ads_dential_surgeries_mgmt_cli.domain.Bill;
import asd.lab6.ads_dential_surgeries_mgmt_cli.repository.BillRepository;

@Profile("prod")
@Repository
public interface JpaBillRepository extends JpaRepository<Bill, Long>, BillRepository {}

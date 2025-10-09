package asd.lab6.ads_dential_surgeries_mgmt_cli.repository.prod;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import asd.lab6.ads_dential_surgeries_mgmt_cli.domain.Address;
import asd.lab6.ads_dential_surgeries_mgmt_cli.repository.AddressRepository;

@Profile("prod")
@Repository
public interface JpaAddressRepository extends JpaRepository<Address, Long>, AddressRepository {}

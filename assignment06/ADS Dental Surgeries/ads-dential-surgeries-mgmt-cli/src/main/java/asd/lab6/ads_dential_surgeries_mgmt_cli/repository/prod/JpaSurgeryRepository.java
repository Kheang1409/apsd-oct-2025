package asd.lab6.ads_dential_surgeries_mgmt_cli.repository.prod;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import asd.lab6.ads_dential_surgeries_mgmt_cli.domain.Surgery;
import asd.lab6.ads_dential_surgeries_mgmt_cli.repository.SurgeryRepository;

@Profile("prod")
@Repository
public interface JpaSurgeryRepository extends JpaRepository<Surgery, Long>, SurgeryRepository {}

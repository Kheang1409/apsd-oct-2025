package asd.lab6.ads_dential_surgeries_mgmt_cli.repository.prod;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import asd.lab6.ads_dential_surgeries_mgmt_cli.domain.Appointment;
import asd.lab6.ads_dential_surgeries_mgmt_cli.repository.AppointmentRepository;

@Profile("prod")
@Repository
public interface JpaAppointmentRepository extends JpaRepository<Appointment, Long>, AppointmentRepository {}

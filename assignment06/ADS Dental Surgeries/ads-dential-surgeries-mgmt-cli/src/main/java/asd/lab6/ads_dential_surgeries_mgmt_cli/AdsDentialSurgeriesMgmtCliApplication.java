package asd.lab6.ads_dential_surgeries_mgmt_cli;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import asd.lab6.ads_dential_surgeries_mgmt_cli.repository.ServiceRepository;
import asd.lab6.ads_dential_surgeries_mgmt_cli.repository.SurgeryRepository;
import asd.lab6.ads_dential_surgeries_mgmt_cli.repository.BillRepository;
import asd.lab6.ads_dential_surgeries_mgmt_cli.domain.Address;
import asd.lab6.ads_dential_surgeries_mgmt_cli.domain.Bill;
import asd.lab6.ads_dential_surgeries_mgmt_cli.domain.Dentist;
import asd.lab6.ads_dential_surgeries_mgmt_cli.domain.Patient;
import asd.lab6.ads_dential_surgeries_mgmt_cli.domain.Service;
import asd.lab6.ads_dential_surgeries_mgmt_cli.domain.Surgery;
import asd.lab6.ads_dential_surgeries_mgmt_cli.domain.User;
import asd.lab6.ads_dential_surgeries_mgmt_cli.repository.AddressRepository;
import asd.lab6.ads_dential_surgeries_mgmt_cli.repository.UserRepository;
import asd.lab6.ads_dential_surgeries_mgmt_cli.service.AppointmentService;
import asd.lab6.ads_dential_surgeries_mgmt_cli.service.DentistService;
import asd.lab6.ads_dential_surgeries_mgmt_cli.service.PatientService;
import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class AdsDentialSurgeriesMgmtCliApplication implements CommandLineRunner {

	private final DentistService dentistService;
	private final PatientService patientService;
	private final AppointmentService appointmentService;
	private final ServiceRepository serviceRepository;
	private final SurgeryRepository surgeryRepository;
	private final BillRepository billRepository;
	private final AddressRepository addressRepository;
	private final UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(AdsDentialSurgeriesMgmtCliApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Create or find sample services
		Service cleaning = serviceRepository.findAll().stream()
				.filter(s -> "Teeth Cleaning".equals(s.getName()))
				.findFirst()
				.orElseGet(() -> {
					Service s = new Service();
					s.setName("Teeth Cleaning");
					return serviceRepository.save(s);
				});

		serviceRepository.findAll().stream()
				.filter(s -> "Cavity Filling".equals(s.getName()))
				.findFirst()
				.orElseGet(() -> {
					Service s = new Service();
					s.setName("Cavity Filling");
					return serviceRepository.save(s);
				});

	// Create or find sample surgery and address
	Address addr = new Address();
	addr.setStreet("123 Main St");
	addr.setCity("Springfield");
	addr.setState("IL");
	addr.setZipCode("62701");

	// persist address explicitly if not already present
	Address savedAddr = addressRepository.findAll().stream()
		.filter(a -> "123 Main St".equals(a.getStreet()) && "62701".equals(a.getZipCode()))
		.findFirst()
		.orElseGet(() -> addressRepository.save(addr));

	Surgery surgery = surgeryRepository.findAll().stream()
		.filter(s -> "Downtown Dental".equals(s.getName()))
		.findFirst()
		.orElseGet(() -> {
		    Surgery sur = new Surgery();
		    sur.setName("Downtown Dental");
		    sur.setTelephoneNumber("+1-555-1234");
					sur.setAddress(savedAddr);
		    return surgeryRepository.save(sur);
		});

			// Create or find dentist (lookup by username)
			Dentist dentist = dentistService.getAll().stream()
					.filter(d -> d.getUser() != null && "alice".equals(d.getUser().getUsername()))
					.findFirst()
					.orElseGet(() -> {
						Dentist d = new Dentist();
						d.setFirstName("Alice");
						d.setLastName("Smith");
						d.setContact("alice.smith@dental.example");
						d.setSpecialization("General Dentistry");
						// try to reuse an existing user if present; otherwise let cascade persist the new user
						var existingUser = userRepository.findAll().stream()
								.filter(u -> "alice".equals(u.getUsername()))
								.findFirst()
								.orElse(null);
						if (existingUser != null) {
							d.setUser(existingUser);
							return dentistService.create(d);
						} else {
							User dUser = new User();
							dUser.setUsername("alice");
							dUser.setPassword("password");
							dUser.setRole("DENTIST");
							d.setUser(dUser);
							// Dentist entity is configured to cascade persist/merge to User, so saving the dentist
							// will persist the new user as well in one operation.
							return dentistService.create(d);
						}
					});

			// Create or find patient (lookup by username)
			Patient patient = patientService.getAll().stream()
					.filter(p -> p.getUser() != null && "bob".equals(p.getUser().getUsername()))
					.findFirst()
					.orElseGet(() -> {
						Patient p = new Patient();
						p.setFirstName("Bob");
						p.setLastName("Jones");
						p.setContact("bob.jones@example.com");
						p.setDateOfBirth(LocalDate.of(1990, 4, 15));
						p.setAddress(savedAddr);
						// try to reuse existing user if present; otherwise rely on cascade from Patient
						var existingPUser = userRepository.findAll().stream()
								.filter(u -> "bob".equals(u.getUsername()))
								.findFirst()
								.orElse(null);
						if (existingPUser != null) {
							p.setUser(existingPUser);
							return patientService.create(p);
						} else {
							User pUser = new User();
							pUser.setUsername("bob");
							pUser.setPassword("password");
							pUser.setRole("PATIENT");
							p.setUser(pUser);
							return patientService.create(p);
						}
					});

			System.out.println("Seeded: dentist id=" + dentist.getId() + ", patient id=" + patient.getId()
					+ ", surgery id=" + surgery.getId() + ", service(id) e.g. " + cleaning.getId());

			// create an appointment in the near future using the created entities
			try {
		// create or find appointment with similar params
		var appointment = appointmentService.getAll().stream()
			.filter(a -> a.getPatient() != null && a.getDentist() != null && a.getService() != null && a.getSurgery() != null)
			.filter(a -> a.getPatient().getId().equals(patient.getId())
				&& a.getDentist().getId().equals(dentist.getId())
				&& a.getService().getId().equals(cleaning.getId())
				&& a.getSurgery().getId().equals(surgery.getId()))
			.findFirst()
			.orElseGet(() -> appointmentService.createAppointment(patient.getId(), dentist.getId(), cleaning.getId(), surgery.getId(), LocalDateTime.now().plusDays(3)));

		System.out.println("Created or found sample appointment for patient " + patient.getId());

		// create or find bill for the appointment
		var existingBill = billRepository.findAll().stream()
			.filter(b -> b.getAppointment() != null && b.getAppointment().getId().equals(appointment.getId()))
			.findFirst();

		if (existingBill.isEmpty()) {
		    Bill bill = new Bill();
		    bill.setAmount(new java.math.BigDecimal("120.00"));
		    bill.setStatus("UNPAID");
		    bill.setAppointment(appointment);
		    billRepository.save(bill);
		    System.out.println("Created bill id=" + bill.getId() + " for appointment id=" + appointment.getId());
		} else {
		    System.out.println("Bill already exists for appointment id=" + appointment.getId());
		}

			} catch (Exception ex) {
				System.out.println("Could not create appointment/bill during seeding: " + ex.getMessage());
			}

		
	}
}

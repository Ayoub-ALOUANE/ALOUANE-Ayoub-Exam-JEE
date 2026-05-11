package com.alouane.ayoub.exam;

import com.alouane.ayoub.exam.entities.*;
import com.alouane.ayoub.exam.enums.*;
import com.alouane.ayoub.exam.services.AccountService;
import com.alouane.ayoub.exam.services.InsuranceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootApplication
public class AlouaneAyoubExamJeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlouaneAyoubExamJeeApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner start(AccountService accountService, InsuranceService insuranceService) {
		return args -> {
			// Security Data
			accountService.addNewRole(new AppRole("USER"));
			accountService.addNewRole(new AppRole("ADMIN"));

			accountService.addNewUser(new AppUser(null, "user", "1234", new ArrayList<>()));
			accountService.addNewUser(new AppUser(null, "admin", "1234", new ArrayList<>()));

			accountService.addRoleToUser("user", "USER");
			accountService.addRoleToUser("admin", "USER");
			accountService.addRoleToUser("admin", "ADMIN");

			// Insurance Data
			Client c1 = insuranceService.saveClient(Client.builder().nom("Ahmed Alami").email("ahmed@gmail.com").build());
			Client c2 = insuranceService.saveClient(Client.builder().nom("Sara Benani").email("sara@gmail.com").build());

			// Automobile Contract
			AutomobileContract auto = new AutomobileContract();
			auto.setClient(c1);
			auto.setDateSouscription(LocalDate.now());
			auto.setStatut(ContractStatus.VALIDE);
			auto.setMontantCotisation(1200.0);
			auto.setDuree(12);
			auto.setTauxCouverture(0.8);
			auto.setMatricule("1234-A-1");
			auto.setMarque("Dacia");
			auto.setModele("Sandero");
			insuranceService.saveContract(auto);

			// Habitation Contract
			HabitationContract hab = new HabitationContract();
			hab.setClient(c1);
			hab.setDateSouscription(LocalDate.now());
			hab.setStatut(ContractStatus.EN_COURS);
			hab.setMontantCotisation(2500.0);
			hab.setDuree(24);
			hab.setTauxCouverture(0.9);
			hab.setTypeLogement(HousingType.APPARTEMENT);
			hab.setAdresse("Casablanca, Maarif");
			hab.setSuperficie(85.0);
			insuranceService.saveContract(hab);

			// Health Contract
			HealthContract health = new HealthContract();
			health.setClient(c2);
			health.setDateSouscription(LocalDate.now());
			health.setStatut(ContractStatus.VALIDE);
			health.setMontantCotisation(800.0);
			health.setDuree(12);
			health.setTauxCouverture(1.0);
			health.setNiveauCouverture(CoverageLevel.PREMIUM);
			health.setNombrePersonnes(4);
			insuranceService.saveContract(health);

			// Payments
			insuranceService.savePayment(Payment.builder()
					.date(LocalDate.now())
					.montant(1200.0)
					.type(PaymentType.PAIEMENT_ANNUEL)
					.contract(auto)
					.build());

			insuranceService.savePayment(Payment.builder()
					.date(LocalDate.now())
					.montant(800.0)
					.type(PaymentType.MENSUALITE)
					.contract(health)
					.build());

			System.out.println("Database successfully seeded with test data.");
		};
	}

}

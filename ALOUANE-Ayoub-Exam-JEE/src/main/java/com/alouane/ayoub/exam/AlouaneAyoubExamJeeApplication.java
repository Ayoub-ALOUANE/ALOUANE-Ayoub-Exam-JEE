package com.alouane.ayoub.exam;

import com.alouane.ayoub.exam.dtos.*;
import com.alouane.ayoub.exam.entities.AppRole;
import com.alouane.ayoub.exam.entities.AppUser;
import com.alouane.ayoub.exam.enums.ContractStatus;
import com.alouane.ayoub.exam.enums.CoverageLevel;
import com.alouane.ayoub.exam.enums.HousingType;
import com.alouane.ayoub.exam.enums.PaymentType;
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

			// Insurance Data using DTOs
			ClientDTO c1 = insuranceService.saveClient(new ClientDTO(null, "Ahmed Alami", "ahmed@gmail.com"));
			ClientDTO c2 = insuranceService.saveClient(new ClientDTO(null, "Sara Benani", "sara@gmail.com"));

			// Automobile Contract
			AutomobileContractDTO auto = new AutomobileContractDTO();
			auto.setMontantCotisation(1200.0);
			auto.setDuree(12);
			auto.setTauxCouverture(0.8);
			auto.setMatricule("1234-A-1");
			auto.setMarque("Dacia");
			auto.setModele("Sandero");
			auto = (AutomobileContractDTO) insuranceService.saveContract(auto, c1.getId());
			insuranceService.changeContractStatus(auto.getId(), ContractStatus.VALIDE);

			// Habitation Contract
			HabitationContractDTO hab = new HabitationContractDTO();
			hab.setMontantCotisation(2500.0);
			hab.setDuree(24);
			hab.setTauxCouverture(0.9);
			hab.setTypeLogement(HousingType.APPARTEMENT);
			hab.setAdresse("Casablanca, Maarif");
			hab.setSuperficie(85.0);
			insuranceService.saveContract(hab, c1.getId());

			// Health Contract
			HealthContractDTO health = new HealthContractDTO();
			health.setMontantCotisation(800.0);
			health.setDuree(12);
			health.setTauxCouverture(1.0);
			health.setNiveauCouverture(CoverageLevel.PREMIUM);
			health.setNombrePersonnes(4);
			health = (HealthContractDTO) insuranceService.saveContract(health, c2.getId());
			insuranceService.changeContractStatus(health.getId(), ContractStatus.VALIDE);

			// Payments
			insuranceService.addPaymentToContract(auto.getId(), new PaymentDTO(null, null, 1200.0, PaymentType.PAIEMENT_ANNUEL));
			insuranceService.addPaymentToContract(health.getId(), new PaymentDTO(null, null, 800.0, PaymentType.MENSUALITE));

			System.out.println("Database successfully seeded with DTO-based test data.");
		};
	}

}

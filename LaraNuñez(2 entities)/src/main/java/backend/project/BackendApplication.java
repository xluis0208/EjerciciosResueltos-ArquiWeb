package backend.project;

import backend.project.entities.LnCompany;
import backend.project.entities.LnSoftware;
import backend.project.repositories.LnCompanyRepository;
import backend.project.repositories.LnSoftwareRepository;
import backend.project.services.LnCompanyService;
import backend.project.services.LnSoftwareService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner mappingDemo(
			LnCompanyRepository lnCompanyRepository,
			LnSoftwareRepository lnSoftwareRepository,
			LnCompanyService lnCompanyService,
			LnSoftwareService lnSoftwareService) {
		return args -> {
			// Crear algunas empresas
			LnCompany lnCompany1 = new LnCompany(0L, "TechSoft SAC", "SAC", LocalDate.of(2020, 5, 12), LocalDate.of(2018, 6, 1), "Activo", null);
			LnCompany lnCompany2 = new LnCompany(0L, "Innova Solutions SRL", "SRL", LocalDate.of(2019, 3, 20), LocalDate.of(2016, 11, 23), "Suspendido", null);

			// Usar el servicio para insertar las empresas
			lnCompany1 = lnCompanyService.insertLnCompany(lnCompany1);
			lnCompany2 = lnCompanyService.insertLnCompany(lnCompany2);

			// Crear algunos softwares para TechSoft SAC
			LnSoftware lnSoftware1 = new LnSoftware(0L, "EduPlus", LocalDate.of(2021, 1, 15), "Educativo", 50000.00, "Web", lnCompany1);
			LnSoftware lnSoftware2 = new LnSoftware(0L, "GameMaster", LocalDate.of(2022, 8, 10), "Juego", 150000.00, "Escritorio", lnCompany1);

			// Crear algunos softwares para Innova Solutions SRL
			LnSoftware lnSoftware3 = new LnSoftware(0L, "HealthTracker", LocalDate.of(2021, 3, 5), "Salud", 80000.00, "Web", lnCompany2);
			LnSoftware lnSoftware4 = new LnSoftware(0L, "SmartHome", LocalDate.of(2022, 12, 2), "Domótica", 95000.00, "Escritorio", lnCompany2);

			// Guardar los softwares usando el servicio
			lnSoftwareService.insertLnSoftware(lnSoftware1);
			lnSoftwareService.insertLnSoftware(lnSoftware2);
			lnSoftwareService.insertLnSoftware(lnSoftware3);
			lnSoftwareService.insertLnSoftware(lnSoftware4);

			// Mostrar las empresas y sus softwares registrados
			List<LnCompany> lnCompanies = lnCompanyRepository.findAll();
			lnCompanies.forEach(lnCompany -> {
				System.out.println("Empresa: " + lnCompany.getLnName());
				lnCompany.getLnSoftwares().forEach(lnSoftware -> {
					System.out.println("Software: " + lnSoftware.getLnBrand() + " (" + lnSoftware.getLnType() + ")");
				});
			});
		};
	}
}
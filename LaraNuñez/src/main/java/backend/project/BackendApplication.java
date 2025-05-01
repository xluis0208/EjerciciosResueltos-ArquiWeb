package backend.project;

import backend.project.entities.Campaign;
import backend.project.repositories.CampaignRepository;
import backend.project.services.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
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
			CampaignRepository campaignRepository,
			CampaignService campaignService) {
		return args -> {
			Campaign campaign1 = new Campaign(0L, "Health Campaign 1", "City A", "Hospital A", LocalDate.of(2023, 5, 15), 500, 5, 10000.0);
			Campaign campaign2 = new Campaign(0L, "Health Campaign 2", "City B", "Hospital B", LocalDate.of(2023, 6, 20), 300, 3, 5000.0);
			Campaign campaign3 = new Campaign(0L, "Health Campaign 3", "City C", "Hospital C", LocalDate.of(2023, 7, 25), 200, 2, 3000.0);
			Campaign campaign4 = new Campaign(0L, "Health Campaign 4", "City D", "Hospital D", LocalDate.of(2023, 8, 10), 800, 7, 15000.0);
			Campaign campaign5 = new Campaign(0L, "Health Campaign 5", "City E", "Hospital E", LocalDate.of(2023, 9, 5), 1000, 10, 20000.0);

			campaignService.insertCampaign(campaign1);
			campaignService.insertCampaign(campaign2);
			campaignService.insertCampaign(campaign3);
			campaignService.insertCampaign(campaign4);
			campaignService.insertCampaign(campaign5);

			List<Campaign> campaigns = campaignRepository.findAll();
			campaigns.forEach(campaign -> {
				System.out.println("Campaign: " + campaign.getLnName() + ", Location: " + campaign.getLnLocation() + ", Hospital: " + campaign.getLnHospital());
			});
		};
	}
}

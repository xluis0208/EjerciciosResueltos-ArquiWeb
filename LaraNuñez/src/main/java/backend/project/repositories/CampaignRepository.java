package backend.project.repositories;

import backend.project.entities.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    List<Campaign> findByLnName(String lnName);
    List<Campaign> findByLnDate(LocalDate lnDate);

}
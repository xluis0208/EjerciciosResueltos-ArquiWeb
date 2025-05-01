package backend.project.services;

import backend.project.dtos.DTOCampaignSummary;
import backend.project.entities.Campaign;

import java.time.LocalDate;
import java.util.List;

public interface CampaignService {
    Campaign insertCampaign(Campaign campaign);
    void deleteCampaign(Long id);
    List<Campaign> findCampaignsByDate(LocalDate lnDate);
    List<Campaign> listAll();
    Campaign findById(Long id);
    long countTotalCampaigns();


}
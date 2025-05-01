package backend.project.servicesimpl;

import backend.project.entities.Campaign;
import backend.project.repositories.CampaignRepository;
import backend.project.services.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CampaignServiceImpl implements CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    @Override
    public Campaign insertCampaign(Campaign campaign) {
        if (campaign.getLnName() == null || campaign.getLnName().isBlank()) {
            throw new IllegalArgumentException("The campaign name cannot be empty.");
        }
        if (campaign.getLnLocation() == null || campaign.getLnLocation().isBlank()) {
            throw new IllegalArgumentException("The campaign location cannot be empty.");
        }
        if (campaign.getLnHospital() == null || campaign.getLnHospital().isBlank()) {
            throw new IllegalArgumentException("The campaign hospital cannot be empty.");
        }
        return campaignRepository.save(campaign);
    }

    @Override
    public void deleteCampaign(Long id) {
        if (campaignRepository.existsById(id)) {
            campaignRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Campaign with ID " + id + " not found.");
        }
    }

    @Override
    public List<Campaign> findCampaignsByDate(LocalDate lnDate) {
        return campaignRepository.findByLnDate(lnDate);
    }

    @Override
    public List<Campaign> listAll() {
        return campaignRepository.findAll();
    }

    @Override
    public Campaign findById(Long id) {
        Optional<Campaign> campaign = campaignRepository.findById(id);
        return campaign.orElse(null);
    }

    @Override
    public long countTotalCampaigns() {
        return campaignRepository.count();
    }
}
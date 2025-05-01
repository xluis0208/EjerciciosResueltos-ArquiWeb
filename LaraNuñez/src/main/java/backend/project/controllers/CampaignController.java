package backend.project.controllers;

import backend.project.dtos.DTOCampaignSummary;
import backend.project.entities.Campaign;
import backend.project.services.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lara/campaigns")
@CrossOrigin("*")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    private DTOCampaignSummary convertToDTO(Campaign campaign) {
        return new DTOCampaignSummary(
                campaign.getId(),
                campaign.getLnName(),
                campaign.getLnLocation(),
                campaign.getLnHospital(),
                campaign.getLnDate(),
                campaign.getLnNumberOfPeopleServed(),
                campaign.getLnNumberOfSpecialties(),
                campaign.getLnAmountPlannedToRaise()
        );
    }

    @GetMapping("/list")
    public ResponseEntity<List<DTOCampaignSummary>> listarTodasLasCampañas() {
        List<Campaign> campaigns = campaignService.listAll();
        if (campaigns.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<DTOCampaignSummary> campaignDTOs = campaigns.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(campaignDTOs, HttpStatus.OK);
    }

    @GetMapping("/date/{lnDate}")
    public ResponseEntity<List<DTOCampaignSummary>> listarCampañasPorFecha(@PathVariable("lnDate") LocalDate lnDate) {
        List<Campaign> campaigns = campaignService.findCampaignsByDate(lnDate);
        if (campaigns.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<DTOCampaignSummary> campaignDTOs = campaigns.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(campaignDTOs, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Campaign> registrarCampaña(@RequestBody Campaign campaign) {
        Campaign newcampaign = campaignService.insertCampaign(campaign);
        return new ResponseEntity<>(newcampaign, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCampaña(@PathVariable("id") Long id) {
        campaignService.deleteCampaign(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOCampaignSummary> obtenerCampañaPorId(@PathVariable("id") Long id) {
        Campaign campaign = campaignService.findById(id);
        if (campaign != null) {
            DTOCampaignSummary campaignDTO = convertToDTO(campaign);
            return new ResponseEntity<>(campaignDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/count")
    public ResponseEntity<Long> obtenerCantidadTotalDeCampañas() {
        long totalcampaign = campaignService.countTotalCampaigns();
        return new ResponseEntity<>(totalcampaign, HttpStatus.OK);
    }
}
package backend.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class DTOCampaignSummary {

    private Long campaignId;
    private String lnName;
    private String lnLocation;
    private String lnHospital;
    private LocalDate lnDate;
    private Integer lnNumberOfPeopleServed;
    private Integer lnNumberOfSpecialties;
    private Double lnAmountPlannedToRaise;
}
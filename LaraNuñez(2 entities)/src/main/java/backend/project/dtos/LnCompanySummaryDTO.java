package backend.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LnCompanySummaryDTO {

    private Long lnCompanyId;
    private String lnCompanyName;
    private Integer lnWebSoftwareCount;
    private Integer lnDesktopSoftwareCount;
    private LocalDate lnDateAffiliation;
    private String lnStatus;
    private Double lnTotalSales;
}
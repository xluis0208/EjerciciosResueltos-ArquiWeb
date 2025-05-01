package backend.project.services;

import backend.project.dtos.LnCompanySummaryDTO;
import backend.project.entities.LnCompany;

import java.time.LocalDate;
import java.util.List;

public interface LnCompanyService {

    LnCompany insertLnCompany(LnCompany lnCompany);
    LnCompany insertLnCompany(String lnName, String lnType, LocalDate lnDateAffiliation, LocalDate lnDateFoundation, String lnStatus);
    void deleteLnCompany(Long id);
    void deleteLnCompany(Long id, boolean forced);
    List<LnCompany> listAllLnCompanies();
    LnCompany findByLnId(Long id);
    List<LnCompany> findByLnStatus(String lnStatus);
    List<LnCompanySummaryDTO> getLnCompaniesSummary();
}
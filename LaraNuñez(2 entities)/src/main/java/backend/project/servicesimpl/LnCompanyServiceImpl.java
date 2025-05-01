package backend.project.servicesimpl;

import backend.project.dtos.LnCompanySummaryDTO;
import backend.project.entities.LnCompany;
import backend.project.repositories.LnCompanyRepository;
import backend.project.services.LnCompanyService;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LnCompanyServiceImpl implements LnCompanyService {

    @Autowired
    private LnCompanyRepository lnCompanyRepository;

    @Override
    public LnCompany insertLnCompany(LnCompany lnCompany) {
        return lnCompanyRepository.save(lnCompany);
    }

    @Override
    public LnCompany insertLnCompany(String lnName, String lnType, LocalDate lnDateAffiliation, LocalDate lnDateFoundation, String lnStatus) {
        LnCompany lnCompany = new LnCompany();
        lnCompany.setLnName(lnName);
        lnCompany.setLnType(lnType);
        lnCompany.setLnDateAffiliation(lnDateAffiliation);
        lnCompany.setLnDateFoundation(lnDateFoundation);
        lnCompany.setLnStatus(lnStatus);

        return insertLnCompany(lnCompany);
    }

    @Override
    public void deleteLnCompany(Long id) {
        lnCompanyRepository.deleteById(id);
    }

    @Override
    public void deleteLnCompany(Long id, boolean forced) {
        LnCompany lnCompany = lnCompanyRepository.findById(id).orElse(null);
        if (lnCompany != null) {
            if (forced) {
                lnCompany.getLnSoftwares().clear();  // Eliminar los softwares asociados antes de eliminar la empresa
                lnCompanyRepository.delete(lnCompany);
            } else {
                lnCompanyRepository.deleteById(id);
            }
        }
    }

    @Override
    public List<LnCompany> listAllLnCompanies() {
        return lnCompanyRepository.findAll();
    }

    @Override
    public LnCompany findByLnId(Long id) {
        return lnCompanyRepository.findById(id).orElse(null);
    }

    @Override
    public List<LnCompany> findByLnStatus(String lnStatus) {
        return lnCompanyRepository.findByLnStatus(lnStatus);
    }

    @Override
    public List<LnCompanySummaryDTO> getLnCompaniesSummary() {
        List<LnCompany> lnCompanies = lnCompanyRepository.findAll();

        return lnCompanies.stream().map(lnCompany -> {
            int lnWebSoftwareCount = (int) lnCompany.getLnSoftwares().stream()
                    .filter(lnSoftware -> "Web".equals(lnSoftware.getLnType()))
                    .count();

            int lnDesktopSoftwareCount = (int) lnCompany.getLnSoftwares().stream()
                    .filter(lnSoftware -> "Escritorio".equals(lnSoftware.getLnType()))
                    .count();

            double lnTotalSales = lnCompany.getLnSoftwares().stream()
                    .mapToDouble(lnSoftware -> lnSoftware.getLnSales() != null ? lnSoftware.getLnSales() : 0)
                    .sum();

            return new LnCompanySummaryDTO(
                    lnCompany.getId(),
                    lnCompany.getLnName(),
                    lnWebSoftwareCount,
                    lnDesktopSoftwareCount,
                    lnCompany.getLnDateAffiliation(),
                    lnCompany.getLnStatus(),
                    lnTotalSales
            );
        }).collect(Collectors.toList());
    }


}
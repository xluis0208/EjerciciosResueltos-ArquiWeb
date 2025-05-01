package backend.project.servicesimpl;

import backend.project.entities.LnSoftware;
import backend.project.repositories.LnSoftwareRepository;
import backend.project.services.LnSoftwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LnSoftwareServiceImpl implements LnSoftwareService {

    @Autowired
    private LnSoftwareRepository lnSoftwareRepository;

    @Override
    public LnSoftware insertLnSoftware(LnSoftware lnSoftware) {
        return lnSoftwareRepository.save(lnSoftware);
    }

    @Override
    public LnSoftware insertLnSoftware(String lnBrand, LocalDate lnDateLaunch, String lnCategory, Double lnSales, String lnType, Long lnCompanyId) {
        LnSoftware lnSoftware = new LnSoftware();
        lnSoftware.setLnBrand(lnBrand);
        lnSoftware.setLnDateLaunch(lnDateLaunch);
        lnSoftware.setLnCategory(lnCategory);
        lnSoftware.setLnSales(lnSales);
        lnSoftware.setLnType(lnType);

        return insertLnSoftware(lnSoftware);
    }

    @Override
    public void deleteLnSoftware(Long id) {
        lnSoftwareRepository.deleteById(id);
    }

    @Override
    public List<LnSoftware> findByLnCategory(String lnCategory) {
        return lnSoftwareRepository.findByLnCategory(lnCategory);
    }

    @Override
    public List<LnSoftware> findByLnYear(int year) {
        return lnSoftwareRepository.findByLnDateLaunchYear(year);
    }

    @Override
    public List<LnSoftware> listAllLnSoftwares() {
        return lnSoftwareRepository.findAll();
    }

    @Override
    public LnSoftware findByLnId(Long id) {
        return lnSoftwareRepository.findById(id).orElse(null);
    }
}
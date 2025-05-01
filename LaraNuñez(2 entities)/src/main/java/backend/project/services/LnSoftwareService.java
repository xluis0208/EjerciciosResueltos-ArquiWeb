package backend.project.services;

import backend.project.entities.LnSoftware;

import java.time.LocalDate;
import java.util.List;

public interface LnSoftwareService {

    LnSoftware insertLnSoftware(LnSoftware lnSoftware);
    LnSoftware insertLnSoftware(String lnBrand, LocalDate lnDateLaunch, String lnCategory, Double lnSales, String lnType, Long lnCompanyId);
    void deleteLnSoftware(Long id);
    List<LnSoftware> findByLnCategory(String lnCategory);
    List<LnSoftware> findByLnYear(int year);
    List<LnSoftware> listAllLnSoftwares();
    LnSoftware findByLnId(Long id);
}
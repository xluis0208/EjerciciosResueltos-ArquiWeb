package backend.project.repositories;

import backend.project.entities.LnCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LnCompanyRepository extends JpaRepository<LnCompany, Long> {

    List<LnCompany> findByLnName(String lnName);
    List<LnCompany> findByLnType(String lnType);
    List<LnCompany> findByLnStatus(String lnStatus);
}
package backend.project.repositories;

import backend.project.entities.LnSoftware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LnSoftwareRepository extends JpaRepository<LnSoftware, Long> {

    List<LnSoftware> findByLnCategory(String lnCategory);
    List<LnSoftware> findByLnType(String lnType);
    List<LnSoftware> findByLnCompanyId(Long lnCompanyId);
    @Query("SELECT s FROM LnSoftware s WHERE YEAR(s.lnDateLaunch) = :year")
    List<LnSoftware> findByLnDateLaunchYear(@Param("year") int year);
}
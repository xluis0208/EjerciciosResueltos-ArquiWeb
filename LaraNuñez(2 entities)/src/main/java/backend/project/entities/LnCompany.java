package backend.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="companies")
public class LnCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lnName;
    private String lnType;
    private LocalDate lnDateAffiliation;
    private LocalDate lnDateFoundation;
    private String lnStatus;

    @JsonIgnore
    @OneToMany(mappedBy = "lnCompany", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<LnSoftware> lnSoftwares;
}